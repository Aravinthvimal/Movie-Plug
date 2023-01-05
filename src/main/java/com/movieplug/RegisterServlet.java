package com.movieplug;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    try {
            
            HttpSession session = request.getSession();  
            session.setAttribute("email", null);
            session.setAttribute("confirmation", null);
            session.setAttribute("showMessage", null);
            
            String uname = request.getParameter("uname");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String mobile = request.getParameter("mobile");
            
            UsersDAO users = new UsersDAO();
            String username = users.signup(uname, email, password, mobile, response, session);
            
            if(!username.equals("")) {
                session.setAttribute("email", email);
                session.setAttribute("UserName", username);
                response.sendRedirect("movies.jsp");
            } else {
                session.setAttribute("flag", null);
                response.sendRedirect("register.jsp");
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
	    
	}

}
