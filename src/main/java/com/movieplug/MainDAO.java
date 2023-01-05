package com.movieplug;

import java.sql.Connection;
import java.sql.DriverManager;

public class MainDAO {
	
	// Connection Object 

	public static Connection connect() throws Exception {
			
		try {
				
			String url = "jdbc:mysql://localhost:3306/movieplug";
			String user = "root";
			String pass = "kannanvasumathi17";
				
			Class.forName("com.mysql.cj.jdbc.Driver");
				
			Connection conn = DriverManager.getConnection(url, user, pass);
			return conn;
			
		} catch (Exception e) {
			System.out.println("Connection failed" + e);
		}
			
		return null;
			
	}

}
