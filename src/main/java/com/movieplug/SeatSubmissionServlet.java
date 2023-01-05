package com.movieplug;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SeatSubmissionServlet")
public class SeatSubmissionServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	    String query;
	    Connection conn;
	    PreparedStatement pstmt;
	    ResultSet rs;
	    
	    try {
	        
	        StringBuilder sb = new StringBuilder("");
	        
	        String[] SeatList = request.getParameterValues("seat");
            PrintWriter out=response.getWriter();
            for(int i = 0; i < SeatList.length; i++) {
                out.print(SeatList[i] + "<br>");
              sb.append(String.valueOf(Integer.parseInt(SeatList[i]) - 1));
              sb.append(" ");
            }
	        
	        HttpSession session = request.getSession(); 
	        conn = MainDAO.connect();
	        
	        query = "select BookedSeats from shows where TheatreName=? and ShowTiming=?";
	        pstmt = conn.prepareStatement(query);
	        pstmt.setString(1, String.valueOf(session.getAttribute("TheatreName")));
	        pstmt.setString(2, String.valueOf(session.getAttribute("ShowTiming")));
	        rs = pstmt.executeQuery();
	        
	        StringBuilder tickets = new StringBuilder("");
	        rs.next();
	        String BookedSeats = rs.getString(1);
	        
	        if(BookedSeats != null) {
	            tickets = sb.append(BookedSeats);
	        } else {
	            tickets = sb;
	        }
	        
	        query = "update shows set BookedSeats=? where TheatreName=? and ShowTiming=?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, String.valueOf(tickets));
            pstmt.setString(2, String.valueOf(session.getAttribute("TheatreName")));
            pstmt.setString(3, String.valueOf(session.getAttribute("ShowTiming")));
            pstmt.execute();
            
            query = "insert into tickets values(?,?,?,?,?,?,?)";
            
            PreparedStatement stmt = conn.prepareStatement(query);
            
            stmt.setString(1, String.valueOf(session.getAttribute("UserName")));
            stmt.setString(2, String.valueOf(session.getAttribute("ShowTiming")));
            stmt.setInt(3, Integer.parseInt(String.valueOf(session.getAttribute("tickets"))));
            stmt.setInt(4, Integer.parseInt(String.valueOf(session.getAttribute("price"))));
            stmt.setString(5, String.valueOf(session.getAttribute("MovieName")));
            stmt.setString(6, String.valueOf(session.getAttribute("TheatreName")));
            stmt.setString(7, String.valueOf(session.getAttribute("ShowDate")));
            
            stmt.executeUpdate();
            
            String UpdateTicketCountQuery = "update shows set AvailableTickets=? where ShowTiming=? and MovieName=? and TheatreName=?";
            
            PreparedStatement ticket = conn.prepareStatement(UpdateTicketCountQuery);
            
            session.setAttribute("AvailableTickets", 
                    Integer.parseInt(String.valueOf(session.getAttribute("AvailableTickets"))) - Integer.parseInt(String.valueOf(session.getAttribute("tickets")))); 
            
            ticket.setInt(1, Integer.parseInt(String.valueOf(session.getAttribute("AvailableTickets"))));
            ticket.setString(2, String.valueOf(session.getAttribute("ShowTiming")));
            ticket.setString(3, String.valueOf(session.getAttribute("MovieName")));
            ticket.setString(4, String.valueOf(session.getAttribute("TheatreName")));
            
            ticket.executeUpdate();
            response.sendRedirect("ticket.jsp");
	        
	    } catch (Exception e) {
	        System.out.println(e);
	    }
		
	}
}
