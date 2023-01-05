package com.movieplug;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
		    
		    HttpSession session = request.getSession();  
			session.setAttribute("email", null);
			session.setAttribute("confirmation",  null);
			session.setAttribute("showMessage", null);
		    
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			System.out.println(email + " " + password);
			
			UsersDAO users = new UsersDAO();
			String username = users.signin(email, password, response, session);
			
			if(!username.equals("")) {
				session.setAttribute("email", email);
				session.setAttribute("UserName", username);
				response.sendRedirect("movies.jsp");
			} else {
			    session.setAttribute("flag", null);
				response.sendRedirect("login.jsp");
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

}
