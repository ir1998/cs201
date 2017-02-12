<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="main.css">
</head>
<body bgcolor="#E6E6FA">
<h1 id="Title1" >Cinemate</h1>
<h2 id="Title2" >Welcome to Cinemate, a movie social media medium</h2>
<%
	if(request.getAttribute("error") == null){
%>
	<h2 id = "Title3">Please input a file so that you may begin</h2>
	<form id="form1" method="GET" action="FirstServlet">
		<h2 id="Title4">File Path: <input type="text" name="file"></h2>
		<div id="ButtonHolder">
			<input id="MyButton" type="submit" value="Submit" name="Submit">
		</div>
	</form>
<%} else{%>
	<h2 id="Title3">Error: <%out.print(request.getAttribute("error")); %></h2>
	<h2 id="Title4" >Please confirm and retry</h2>
	<% request.setAttribute("error", null); %>
	<form id="form" method="GET" action="GetFile.jsp">
		<div id="ButtonHolder">
			<input id="MyButton" type="submit" value="Confirm" name="Submit">
		</div>
	</form>
	
<%}%>
</body>
</html>