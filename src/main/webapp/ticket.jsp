<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <title> Movie Plug - Ticket Preview </title>
  <meta name="viewport" content="width=device-width, initial-scale=1"><link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<link rel="stylesheet" href="./style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>

</head>
<body>
<!-- partial:index.partial.html -->

<div style="position : relative" class="ticket">
	<div class="left">
		<div class="image">
			<p class="admit-one">
				<span> MOVIE PLUG </span>
				<span> MOVIE PLUG </span>
				<span> MOVIE PLUG </span>
			</p>
			<div class="ticket-number">
				<p>
					#20030220
				</p>
			</div>
		</div>
		<div class="ticket-info">
			<p class="date">
				<span>SCREEN # ${Screen} </span>
				<span class="june-29"> ${ShowDate} </span>
				<span>2022</span>
			</p>
			<div class="show-name">
				<h1> ${MovieName} </h1>
				<h2> ${TheatreName} </h2>
			</div>
			<div class="time">
				<p> ${ShowTiming} <span>TO</span> ${ShowEndTime} </p>
				<p> TICKETS <span> # </span> ${tickets} </p>
			</div>
			<p class="location"><span> ${TheatreName} </span>
				<span class="separator"><i class="far fa-smile"></i></span><span> RS. ${price} </span>
			</p>
		</div>
	</div>
	<div class="right">
		<p class="admit-one">
			<span> MOVIE PLUG </span>
			<span> MOVIE PLUG </span>
			<span> MOVIE PLUG </span>
		</p>
		<div class="right-info-container">
			<div class="show-name">
				<h1> ${MovieName} </h1>
			</div>
			<div class="time">
				<p> ${ShowTiming} <span>TO</span> ${ShowEndTime} </p>
				<p> TICKETS <span> # </span> ${tickets} </p>
			</div>
			<div class="barcode">
				<img src="https://external-preview.redd.it/cg8k976AV52mDvDb5jDVJABPrSZ3tpi1aXhPjgcDTbw.png?auto=webp&s=1c205ba303c1fa0370b813ea83b9e1bddb7215eb" alt="QR code">
			</div>
			<p class="ticket-number">
				RS. ${price}
			</p>
		</div>
	</div>
</div>

<div style="display: flex; gap: 5px; position:absolute; bottom:10px; right: 10px;">
	<form action="logout">
		<button style="padding: 5px 10px;"> LOGOUT </button>
	</form>
	<form action="home">
		<button style="padding: 5px 10px;"> HOME </button>
	</form>
</div>


<!-- partial -->
  <script  src="./script.js"></script>

</body>
</html>
