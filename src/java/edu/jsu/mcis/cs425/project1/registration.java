
package edu.jsu.mcis.cs425.project1;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.*;
/**
 *
 * @author edecroes
 */


@WebServlet(name = "registration", urlPatterns = {"/registration"})
public class registration extends HttpServlet {
     
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        Database db = null;
        Connection connection;
        PreparedStatement pstatement = null;
        ResultSet resultset = null;
        
        String query;
        String parameter;
        String table = "";
        //String firstName = request.getParameter("First Name");
        //String lastName = request.getParameter("Last Name");

        //processRequest(request, response);
        
        boolean hasresults;
        try{
        //db = new Database();
            connection = db.getConnection();

            parameter = request.getParameter("search");
            int parameterAgain = Integer.parseInt(parameter);
            
            query = "SELECT * FROM people WHERE lastname = ?";
            
            pstatement = connection.prepareStatement(query);
            pstatement.setString(1, parameter);
            
            hasresults = pstatement.execute();
            
            while ( hasresults || pstatement.getUpdateCount() != -1 ) {
                
                if ( hasresults ) {
                    resultset = pstatement.getResultSet();
                    table += db.getResult(resultset);
                }
                
                else {
                    
                    if ( pstatement.getUpdateCount() == -1 ) {
                        break;
                    }
                    
                }

                hasresults = pstatement.getMoreResults();
            
            }

            out.println("<p>Search Parameter: " + parameter + "</p>" + table);
        }
        
        catch(Exception e){
            System.out.println(e.toString());
        }

        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        doGet(request, response);
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
