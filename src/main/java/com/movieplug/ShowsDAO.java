package com.movieplug;

import java.sql.*;
import java.util.*;

public class ShowsDAO {

    Connection conn;
    PreparedStatement pstmt;
    String query;
    ResultSet rs;
    
    public List<Movie> getAllMovies() {
        
        List<Movie> list = new ArrayList<>();
        
        try {
            
            conn = MainDAO.connect();
            
            query = "select * from movies where Shows > 0";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            
            while(rs.next()) {
                Movie movie = new Movie();
                movie.setId(rs.getInt("m_id"));
                movie.setName(rs.getString("MovieName"));
                list.add(movie);
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return list;
        
    }
    
    public List<Theatres> getAllTheatres(int MovieId) {
        
        List<Theatres> list = new ArrayList<>();
        
        try {
            
            conn = MainDAO.connect();
            
            query = "select * from theatre where m_id=?";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, MovieId);
            rs = pstmt.executeQuery();
            
            while(rs.next()) {
                Theatres theatre = new Theatres();
                theatre.setId(rs.getInt("id"));
                theatre.setMovieId(rs.getInt("m_id"));
                theatre.setName(rs.getString("TheatreName"));
                list.add(theatre);
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return list;
        
    }
    
    public List<Shows> getAllDates(int TheatreId) {
        
        List<Shows> list = new ArrayList<>();
        
        try {
            
            conn = MainDAO.connect();
            
            query = "select * from shows where t_id=?";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, TheatreId);
            rs = pstmt.executeQuery();
            
            while(rs.next()) {
                Shows show = new Shows();
                show.setId(rs.getInt("id"));
                show.setMovieId(rs.getInt("m_id"));
                show.setTheatreId(rs.getInt("t_id"));
                show.setShowTime(rs.getString("ShowTiming"));
                list.add(show);
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return list;
        
    }
    
}
