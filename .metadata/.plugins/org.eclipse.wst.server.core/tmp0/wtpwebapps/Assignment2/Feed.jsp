<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="data.User,data.Event, java.util.List,java.util.Set" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="main.css">
</head>
<body bgcolor="#E6E6FA">
<h1 id="Title1" >Cinemate</h1>
<form method="GET" action="MainMenu.jsp" >
	<div id="ButtonHolder">
		<input id="MyButton" type="submit" value="Return to Main Menu">
	</div>
</form>
<br>
<div id="imageBox">
	<img id="circleImage" src=<%out.print(((User)(session.getAttribute("user"))).getImage());%>>
</div>
<h2 id="Title5"><%out.print(((User)(session.getAttribute("user"))).getName());%></h2>
<h2 id="Title5">@<%out.print(session.getAttribute("username"));%></h2>
</head>
<body>
		<h3 id="Title2">FEED</h3>
		<% List<Event> feed = (List<Event>)((User)(session.getAttribute("user"))).getFeed(); %>
		<div id="ResultsBox">
			<%for(Event e: feed){ %>
				<h3 id="Results"><%out.print(e.getEventString()); %></h3>
			<%} %>
		</div>

</body>
</html>