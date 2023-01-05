<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Movie Plug - Login </title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
        <style type="text/css">
            body{
                background: url(image/roenkae.jpg);
                background-size: cover;
            }
            .drop-down-list{
                margin: 150px auto;
                width: 50%;
                padding: 30px;
            }
        </style>
    </head>
   
    <body class="cyan">
        <div class="container">
            <div class="drop-down-list card">
                <div class="center">
                    <h5> Login to MoviePlug </h5>
                </div>
                <div class="divider"></div><br>
                <form style="position:relative; padding-bottom:25px;" action="login" >
                    <div class="input-field">
                        <label for="email" > User email : </label>
        				<input type="email" name="email"> <br>
                    </div>
                    <div class="input-field">
                        <label for="password" > Password :  </label>
        				<input type="password" name="password"> <br>
                    </div>
                    <div class="center">
                        <button type="submit" class="btn">Submit</button>
                    </div>
                </form>
                
                <% 
                
                String message = String.valueOf(session.getAttribute("message")); 
			    session.setAttribute("MovieName", null);
			    
			    %>
			    
			    <% if(!message.equals(null)) { %>
			       <p style="color:red;" > ${message} </p>
			    <% } %>
                
                <p style="color:gray; font-size:12px; position:absolute; bottom:0.5px;"> 
                	Don't have an account ? 
                	<a href="register.jsp" > Register here </a> 
                </p>
                
                <p style="color:gray; font-size:12px; position:absolute; bottom:0.5px; right: 30px"> 
                	Login as Admin 
                	<a href="adminLogin.jsp" > Click here </a> 
                </p>
    			
            </div>
        </div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
	</body>
</html>