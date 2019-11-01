package edu.jsu.mcis.cs425.project1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class Database{
    
    public Connection getConnection(){
        
       Connection conn = null;
       
        try {
            
            Context envContext = new InitialContext();
            Context initContext  = (Context)envContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)initContext.lookup("jdbc/db_pool");
            conn = ds.getConnection();
            
        }        
        catch (Exception e) {}
        
        return conn;
        
    }
    
    public String getRegistrationTable(int sessionid) {
        
        String result = "";
        
        try {
        
            Connection connection = getConnection();
            
            String query = "SELECT * FROM registrations WHERE sessionid = ?";
            
            PreparedStatement pstatement = connection.prepareStatement(query);
            pstatement.setInt(1, sessionid);
            
            boolean hasresults = pstatement.execute();            
                
            if ( hasresults ) {
                ResultSet resultset = pstatement.getResultSet();
                result += getResultSetTable(resultset);
            }

        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        return result;
        
    }
    
    public String addRegistration(String fname, String lname, String dname, int sessionid) {
        
        String result = "";
        
        try {
            
            int key = 0;
        
            Connection connection = getConnection();
            
            String query = "INSERT INTO registrations (firstname, lastname, displayname, sessionid) VALUES (?, ?, ?, ?)";
            
            PreparedStatement pstatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            pstatement.setString(1, fname);
            pstatement.setString(2, lname);
            pstatement.setString(3, dname);
            pstatement.setInt(4, sessionid);
            
            int rows = pstatement.executeUpdate();
            
            if (rows == 1) {
                ResultSet keys = pstatement.getGeneratedKeys();
                if (keys.next()) {
                    key = keys.getInt(1);
                }
            }
            
            JSONObject json = new JSONObject();
            
            json.put("displayname", dname);
            json.put("code", ( "R" + String.format("%06d", key) ));
            
            result = JSONValue.toJSONString(json);

        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        return result;
        
    }


    public String getResultSetTable(ResultSet resultset) throws ServletException, IOException {
        
        ResultSetMetaData metadata = null;
        
        String table = "";
        String tableheading;
        String tablerow;
        
        String key;
        String value;
        
        try {
            
            System.out.println("*** Getting Query Results ... ");

            metadata = resultset.getMetaData();

            int numberOfColumns = metadata.getColumnCount();
            
            table += "<table border=\"1\">";
            tableheading = "<tr>";
            
            System.out.println("*** Number of Columns: " + numberOfColumns);
            
            for (int i = 1; i <= numberOfColumns; i++) {
            
                key = metadata.getColumnLabel(i);
                
                tableheading += "<th>" + key + "</th>";
            
            }
            
            tableheading += "</tr>";
            
            table += tableheading;
                        
            while(resultset.next()) {
                
                tablerow = "<tr>";
                
                for (int i = 1; i <= numberOfColumns; i++) {

                    value = resultset.getString(i);

                    if (resultset.wasNull()) {
                        tablerow += "<td></td>";
                    }

                    else {
                        tablerow += "<td>" + value + "</td>";
                    }
                    
                }
                
                tablerow += "</tr>";
                
                table += tablerow;
                
            }
            
            table += "</table><br />";

        }
        
        catch (Exception e) {}
        
        return table;
        
    } // End getResultSetTable()

}
