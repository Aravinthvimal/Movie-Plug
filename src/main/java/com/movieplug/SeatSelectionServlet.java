package com.movieplug;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SeatSelection")
public class SeatSelectionServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	    try {
	        
	        HttpSession session = request.getSession();
	        
	        Connection conn = MainDAO.connect();
	        String query = "select BookedSeats from shows where TheatreName=? and ShowTiming=?";
	        PreparedStatement pstmt = conn.prepareStatement(query);
	        pstmt.setString(1, String.valueOf(session.getAttribute("TheatreName")));
	        pstmt.setString(2, String.valueOf(session.getAttribute("ShowTiming")));
	        ResultSet rs = pstmt.executeQuery();
	        
	        rs.next();
	        String BookedSeats = rs.getString(1);
	        session.setAttribute("BookedSeats", BookedSeats);
	        
	        response.sendRedirect("selectTickets.jsp"); 
	        
	    } catch(Exception e) {
	        System.out.println(e);
	    }
	    
	    
	}
}
