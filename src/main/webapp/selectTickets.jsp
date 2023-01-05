<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title> Movie Plug - Seat Selection </title>
		<link rel="stylesheet" href="./seatstyle.css">
		<style>
			@import url('https://fonts.googleapis.com/css2?family=Roboto:wght@100&display=swap');
		</style>
	</head>
	
	<body>
	
		
		<form action="select">
		
			<h2> <b> Select your Tickets </b> </h2>
			<hr class="solid">
			<c:set var = "index" scope = "session" value = "${0}"/>
			<div>
			
			<%
			
			String BookedTickets = String.valueOf(session.getAttribute("BookedSeats"));
			List<String> BookedTicketList = Arrays.asList(BookedTickets.split(" "));
			
			int n = Integer.parseInt(String.valueOf(session.getAttribute("TotalTickets")));
			int num = 0;
			
			for(int i = 0; i < n/10; i++) {
				for(int j = 0; j < 10; j++) { 
				
				if(BookedTicketList.contains(String.valueOf(num))) { %>
					<c:set var = "index" scope = "session" value = "${index + 1}"/>
					<input class="seat-checkbox" name="seat" type="checkbox" value="${index}" disabled style="display:inline; cursor: not-allowed; ">
				<% } else { %> 
					<c:set var = "index" scope = "session" value = "${index + 1}"/>
					<input class="seat-checkbox" name="seat" type="checkbox" value="${index}" style="display:inline;"> <% } %>
				
				<% num++; } %>
			
				<br>
				
			<% } %>
			
			</div>
			<hr class="screen">
			<p class="selected-text"> Selected : <span class="selected-count" > 0 </span> / ${tickets} </p>
			
			<button type="submit" > SUBMIT </button>
			
		</form>
		
	</body>
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.js"></script>
	<script type="text/javascript">
	
	var limit = ${tickets};
	$('input.seat-checkbox').on('change', function(evt) {
	   if($(this).siblings(':checked').length >= limit) {
	       this.checked = false;
	   } if($(this).siblings(':checked').length < limit) 
		   document.querySelector(".selected-count").innerHTML = $(this).siblings(':checked').length + 1;
	   	   document.querySelector(".selected-text").style.color = "black";
	   if($(this).siblings(':checked').length == limit - 1) {
		   document.querySelector(".selected-text").style.color = "red";
	   }
	});
	
	</script>
	
</html>