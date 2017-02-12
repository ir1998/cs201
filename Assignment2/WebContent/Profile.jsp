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
<div id="column">
    <div id="leftColumn">
        <% Set<String> followers = (Set<String>)((User)(session.getAttribute("user"))).getFollowers();%>
		<div id="ResultsBox">
			<%for(String f: followers){ %>
				<h3 id="Results"><%out.print(f); %></h3>
			<%} %>
		</div>
    </div>
    <div id="rightColumn">
        <% Set<String> following = (Set<String>)((User)(session.getAttribute("user"))).getFollowing();%>
		<div id="ResultsBox">
			<%for(String f: following){ %>
				<h3 id="Results"><%out.print(f); %></h3>
			<%} %>
    </div>
</div>
</body>
</html>