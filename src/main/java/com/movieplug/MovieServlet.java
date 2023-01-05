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

@WebServlet("/MovieServlet")
public class MovieServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	    Connection conn;
	    String query;
	    PreparedStatement pstmt;
	    ResultSet rs;
	    
	    try {
	        
	        conn = MainDAO.connect();
	        HttpSession session = request.getSession();
	        
	        
	        int MovieId = Integer.parseInt(request.getParameter("movie"));
	        int TheatreId = Integer.parseInt(request.getParameter("theatre"));
	        int ShowId = Integer.parseInt(request.getParameter("show"));
	        String ShowDate = request.getParameter("date");
	        int tickets = Integer.parseInt(request.getParameter("tickets"));
	         
	        query = "select MovieName from movies where m_id=?";
	        pstmt = conn.prepareStatement(query);
	        pstmt.setInt(1, MovieId);
	        rs = pstmt.executeQuery();
	        rs.next();
	        
	        session.setAttribute("MovieName", rs.getString(1));
	        
	        query = "select TheatreName from theatre where id=?";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, TheatreId);
            rs = pstmt.executeQuery();
            rs.next();
	        
	        session.setAttribute("TheatreName", rs.getString(1));
	        
	        query = "select ShowTiming from shows where id=?";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, ShowId);
            rs = pstmt.executeQuery();
            rs.next();
            
            String ShowEndTiming = String.valueOf(Integer.parseInt(rs.getString(1).substring(0,2)) + 3) 
                    + ":" + String.valueOf(rs.getString(1).substring(3));
	        
            System.out.println(ShowEndTiming);
            
	        session.setAttribute("ShowTiming", rs.getString(1));
	        session.setAttribute("ShowEndTime", ShowEndTiming);
	        session.setAttribute("ShowDate", ShowDate);
	        session.setAttribute("tickets", tickets);
	        
	        query = "select AvailableTickets from shows where MovieName=? and ShowTiming=? and TheatreName=?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, String.valueOf(session.getAttribute("MovieName")));
            pstmt.setString(2, String.valueOf(session.getAttribute("ShowTiming")));
            pstmt.setString(3, String.valueOf(session.getAttribute("TheatreName")));
            
            rs = pstmt.executeQuery();
            rs.next();
            int available_tickets = rs.getInt(1);
            
            if(tickets <= available_tickets) {
                session.setAttribute("confirmation", "available");
                response.sendRedirect("movies.jsp");
            } else {
                session.setAttribute("showMessage", "Tickets not availalble, only " + available_tickets + " tickets available");
                response.sendRedirect("movies.jsp");
            }
            
            query = "select TicketPrice from theatre where MovieName=? and TheatreName=?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, String.valueOf(session.getAttribute("MovieName")));
            pstmt.setString(2, String.valueOf(session.getAttribute("TheatreName")));
            
            rs = pstmt.executeQuery();
            rs.next();
            int price = rs.getInt(1) * tickets;
            
            session.setAttribute("AvailableTickets", available_tickets);
            session.setAttribute("price", price);
            
            query = "select * from theatre where TheatreName=? and MovieName=?;";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, String.valueOf(session.getAttribute("TheatreName")));
            pstmt.setString(2,  String.valueOf(session.getAttribute("MovieName")));
            rs = pstmt.executeQuery();
            rs.next();
            
            session.setAttribute("Screen", rs.getInt("Screen"));
            session.setAttribute("TotalTickets", rs.getInt("TotalTickets"));
	        
	        System.out.println(session.getAttribute("MovieName") + " " 
	        + session.getAttribute("TheatreName") + " " 
	        + session.getAttribute("ShowTiming") + " " + ShowDate + " " + tickets + " screen :  " + session.getAttribute("Screen"));
	        
	    } catch (Exception e) {
	        System.out.println(e);
	    }
	    
	}
}
