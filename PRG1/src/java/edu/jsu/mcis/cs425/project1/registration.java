
package edu.jsu.mcis.cs425.project1;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
/**
 *
 * @author edecroes
 */


@WebServlet(name = "registration", urlPatterns = {"/registration"})
public class registration extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet registration</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet registration at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstName = request.getParameter("First Name");
        String lastName = request.getParameter("Last Name");

        //processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("<table>");
        out.println("<tr>");
        out.println("<td> First Name: </td>");
        out.println("<td><input type=""text"" name =""First Name""></td>"");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td> Last Name: </td>");
        out.println("<td><input type="text" name ="Last Name"></td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td> How your name will be displayed: </td>");
        out.println("<td><input type="text" name ="Name Display"></td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td> Select Session: </td>");
        out.println("<td><input type="text" name ="session"></td>");
        out.println("</tr>");
        out.println("</table>");
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
