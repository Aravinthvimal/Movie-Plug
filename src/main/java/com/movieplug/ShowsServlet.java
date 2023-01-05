package com.movieplug;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/ShowsServlet")
public class ShowsServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    try(PrintWriter out = response.getWriter()) {
	        
	        ShowsDAO show = new ShowsDAO();
	        String op = request.getParameter("operation");
	        
	        if(op.equals("movie")) {
	            List<Movie> mList = show.getAllMovies();
	            Gson json = new Gson();
	            String MovieList = json.toJson(mList);
	            response.setContentType("text/html");
	            response.getWriter().write(MovieList);
	        }
	        
	        if(op.equals("theatre")) {
	            int id = Integer.parseInt(request.getParameter("id"));
                List<Theatres> tList = show.getAllTheatres(id);
                Gson json = new Gson();
                String TheatreList = json.toJson(tList);
                response.setContentType("text/html");
                response.getWriter().write(TheatreList);
            }
	        
	        if(op.equals("show")) {
                int id = Integer.parseInt(request.getParameter("id"));
                List<Shows> sList = show.getAllDates(id);
                Gson json = new Gson();
                String ShowList = json.toJson(sList);
                response.setContentType("text/html");
                response.getWriter().write(ShowList);
	        }
	        
	    } catch (Exception e) {
            System.out.println(e);
        }
	    
	}

}
