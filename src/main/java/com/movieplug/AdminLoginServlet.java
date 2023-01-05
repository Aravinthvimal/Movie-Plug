package com.movieplug;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		try {
            
            HttpSession session = request.getSession();  
            session.setAttribute("email", null);
            session.setAttribute("showMessage", null);
            
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String action = request.getParameter("action");
            
            if(email.equals("aravinthvimal08@gmail.com")) {
                if(password.equals("kannanvasumathi17")) {
                    session.setAttribute("email", email);
                    session.setAttribute("UserName", "Aravinth");
                    response.sendRedirect("movies.jsp");
                } else {
                    session.setAttribute("message", "Incorrect password, try again");
                    session.setAttribute("flag", null);
                    response.sendRedirect("adminLogin.jsp");
                }
            } else {
                session.setAttribute("message", "Admin not found, users login through the below link");
                session.setAttribute("flag", null);
                response.sendRedirect("adminLogin.jsp");
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
	}
}
