package com.movieplug;

import java.util.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;

class Users {
	
	String UserID;
	String UserName;
	String email;
	String password;
	String mobile;
	
}

public class UsersDAO {
	
	Scanner sc = new Scanner(System.in);
	
	public String signup(String username, String email, String pass, String mobile, HttpServletResponse response, HttpSession session) throws Exception {
		
		try {
			
			// User Data inserted into users table in Database
			
			String query = "insert into users values (?,?,?,?)";
			
			Connection conn = MainDAO.connect();
			PreparedStatement stmt = conn.prepareStatement(query);
			
			stmt.setString(1, username);
			stmt.setString(2, email);
			stmt.setString(3, pass);
			stmt.setString(4, mobile);
			
			stmt.executeUpdate();
			session.setAttribute("username", username);
			response.sendRedirect("movies.jsp");
			
			stmt.close();
			conn.close();
			
			return username;
			
		} catch (SQLIntegrityConstraintViolationException e) {
			
			// Check whether user already exists in the database
		    session.setAttribute("message", "User Already Exists");
		    session.setAttribute("username", null);
            session.setAttribute("flag", null);
            response.sendRedirect("login.jsp");
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return null;
		
		
	}
	
	public String signin(String email, String pass, HttpServletResponse response, HttpSession session) throws Exception {
		
		try {
			
			// Fetching user email and password for verification
			
			String findQuery = "select * from users where Email=?";
			
			Connection conn = MainDAO.connect();
			PreparedStatement pstmt = conn.prepareStatement(findQuery);
			pstmt.setString(1, email);
			ResultSet rs;
			
			String UserName = "";
			
			try {
				
				rs = pstmt.executeQuery();
				rs.next();
				UserName = rs.getString(1);
				
				// User email and password verification
				
				System.out.println(email + " " + pass);
				System.out.println(rs.getString(2) + " " + rs.getString(3));
				
				if(email.equals(rs.getString(2)) && pass.equals(rs.getString(3))) {
					session.setAttribute("username", UserName);
					session.setAttribute("flag", 1);
				} else {
				    session.setAttribute("message", "Incorrect email or password, try again");
					session.setAttribute("flag", null);
					response.sendRedirect("login.jsp");
				}
				
			} catch (SQLException e) {
				
				// Handling users not on the database and opts into login option
				
			    session.setAttribute("message", "User does not exists, register user");
			    session.setAttribute("flag", null);
                response.sendRedirect("register.jsp");
				//return signup();
			}
			
			pstmt.close();
			conn.close();
			
			return UserName;
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return null;
		
	}
}
