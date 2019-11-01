
package edu.jsu.mcis.cs425.project1;

import javax.servlet.annotation.WebServlet;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author edecroes
 */


@WebServlet(name = "registration", urlPatterns = {"/registration"})
public class Registration extends HttpServlet {
     
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        
        response.setContentType("text/html;charset=UTF-8");
        
        try {
            
            Database db = new Database();

            PrintWriter out = response.getWriter();

            int sessionid = Integer.parseInt(request.getParameter("sessionid"));
            
            out.println( db.getRegistrationTable(sessionid) );
            
        }
        catch (Exception e) { e.printStackTrace(); }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        
        response.setContentType("application/json;charset=UTF-8");
        
        try {
            
            Database db = new Database();

            PrintWriter out = response.getWriter();

            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String displayname = request.getParameter("displayname");
            int sessionid = Integer.parseInt(request.getParameter("sessionid"));
            
            out.println( db.addRegistration(firstname, lastname, displayname, sessionid) );
            
        }
        catch (Exception e) { e.printStackTrace(); }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
