package edu.jsu.mcis.cs425.project1;

import java.sql.ResultSetMetaData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.naming.Context;
import javax.sql.DataSource;
import javax.naming.InitalContext;
import java.sql.Driver;
import static org.apache.tomcat.jni.User.username;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class Database{
    private Connection getConnection(){
       Connection conn = null;
        
        try{
            Context envContext = new InitalContext();
            Context initContext = (Context)envContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)initContext.lookup
            ("jdbc:mysql//localhost/registration");
            conn = ds.getConnection();
        }
        catch(Exception e){e.printStackTrace();}
        return conn;
    }
}

Class.f
    String url = "jdbc:mysql//localhost/registration";
    
    query = "SELECT * FROM badge WHERE id ='3282F212'";
    PreparedStatement pstmt = conn.prepareStatement(query);


