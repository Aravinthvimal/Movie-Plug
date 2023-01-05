<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Movie Plug - Movies </title>
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
            
            .logouthome {
				background-color : #2BBBAD;
				outline: 2px solid transparent;
				outline-offset: 2px;
				border : 0px solid transparent;
				color : white;
				box-shadow: 0 5px 8px rgb(0 0 0 / 0.2);
				cursor : pointer;
			}
			            
        </style>
    </head>
    
   <%
	
	if(session.getAttribute("email") == null) {
		response.sendRedirect("login.jsp");
	}
	
	%>
   
    <body class="cyan">
        <div class="container">
            <div class="drop-down-list card">
                <div class="center">
                    <h5> Movie Plug </h5>
                </div>
                <div class="divider"></div>
                <% if(session.getAttribute("confirmation") == null) { %>
                <form style="padding-bottom:20px;" action="movies" >
                    <div class="input-field">
                        <select name="movie" id="movie">
                            <option>Select Movie </option>
                        </select>
                    </div>
                    <div class="input-field">
                        <select name="theatre" id="theatre">
                            <option>Select Theater </option>
                        </select>
                    </div>
                    <div>
                        <label style="font-size:14px;" for="date"> Select Date </label>
                        <input type="date" name="date" min="2022-10-31" max="2022-11-05"/>
                    </div>
                    <div class="input-field">
                        <select name="show" id="show">
                            <option>Select Show </option>
                        </select>
                    </div>
                    <div class="input-field">
                        <label style="color:black;" for="tickets"> Number of Tickets </label>
                        <input type="number" name="tickets"/>
                    </div>
                    <div class="center">
                        <button class="btn">Submit</button>
                    </div>
                    <p style="position:absolute; bottom:0.5px; color:red; font-size:12px;" > ${showMessage} </p>
                </form>
                <% } else { %>
                <form style="padding-bottom:20px;" action="selection" >
                    <div class="input-field">
                        <p> User Name : ${UserName} </p>
                    </div>
                    <div class="input-field">
                    	<p> Theater Name : ${TheatreName} </p>
                    </div>
                    <div class="input-field">
                    	<p> Movie Name : ${MovieName} </p>
                    </div>
                    <div class="input-field">
                       	<p> Show date and time : ${ShowDate} @ ${ShowTiming} </p>
                    </div>
                    <div class="input-field">
                        <p> Tickets : ${tickets} </p>
                    </div>
                    <div class="input-field">
                        <p> Price : ${price} </p>
                    </div>
                    <div class="center">
                        <button class="btn"> Confirm </button>
                    </div>
                </form>
                <% } %>
            </div>
        </div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                $.ajax({
                    url: "ShowsServlet",
                    method: "GET",
                    data: {operation: 'movie'},
                    success: function (data, textStatus, jqXHR) {
                        console.log(data);
                        let obj = $.parseJSON(data);
                        $.each(obj, function (key, value) {
                            $('#movie').append('<option value="' + value.id + '">' + value.name + '</option>')
                        });
                        $('select').formSelect();
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        $('#movie').append('<option> Movie Unavailable </option>');
                    },
                    cache: false
                });


                $('#movie').change(function () {
                    $('#theatre').find('option').remove();
                    $('#theatre').append('<option>Select Theatre</option>'); 
                    $('#show').find('option').remove();
                    $('#show').append('<option>Select Date</option>');

                    let cid = $('#movie').val();
                    let data = {
                        operation: "theatre",
                        id: cid
                    };

                    $.ajax({
                        url: "ShowsServlet",
                        method: "GET",
                        data: data,
                        success: function (data, textStatus, jqXHR) {
                            console.log(data);
                            let obj = $.parseJSON(data);
                            $.each(obj, function (key, value) {
                                $('#theatre').append('<option value="' + value.id + '">' + value.name + '</option>')
                            });
                            $('select').formSelect();
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            $('#movie').append('<option>Theatre Unavailable</option>');
                        },
                        cache: false
                    });
                });
                
                $('#theatre').change(function () {
                    $('#show').find('option').remove();
                    $('#show').append('<option>Select Show</option>');

                    let sid = $('#theatre').val();
                    let data = {
                        operation: "show",
                        id: sid
                    };

                    $.ajax({
                        url: "ShowsServlet",
                        method: "GET",
                        data: data,
                        success: function (data, textStatus, jqXHR) {
                            console.log(data);
                            let obj = $.parseJSON(data);
                            $.each(obj, function (key, value) {
                                $('#show').append('<option value="' + value.id + '">' + value.name + '</option>')
                            });
                            $('select').formSelect();
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            $('#show').append('<option>Show Unavailable</option>');
                        },
                        cache: false
                    });
                });

            });
        </script>
        <div style="display: flex; gap: 5px; position:absolute; bottom:10px; right: 10px;">
			<form action="logout">
				<button class="logouthome" style="padding: 5px 10px;"> LOGOUT </button>
			</form>
			<form action="home">
				<button class="logouthome" style="padding: 5px 10px;"> HOME </button>
			</form>
		</div>
    </body>
</html>

  