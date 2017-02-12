<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="data.User,java.util.Set" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="main.css">
</head>
<body bgcolor="#E6E6FA">
<h1 id="Title1" >Cinemate</h1>
<h2 id="Title2" >Enter a username or name to search</h2>
</head>
<body>
	<form id="form1" method="GET" action="MainMenuServlet">
		<div id="ButtonHolder">
			<input type="text" name="searchedUser">
			<input id="MyButton" type="submit" value="Search" name="searchUsers">
		</div>
		<br>
		<div id="ButtonHolder">
			<input id="MyButton" type="submit" value="Back to Main Menu">
		</div>
	</form>
<%	if(session.getAttribute("searchResults") != null){%>
		<% Set<User> userResults = (Set<User>)session.getAttribute("searchResults"); %>
		<div id="ResultsBox">
			<h4 id="Title3">Search Results</h4>
			<% for(User u: userResults){%>
				<h4 id="Results"> <%out.print(u.getUsername());%> </h4>
			<%}
			session.setAttribute("searchResults", null); %>	
		</div>
	<%}else if(session.getAttribute("multipleSearch") != null) {%>
			<div id="ResultsBox">
				<h4 id="Title3">No Search Results Found</h4>
			</div>
	<%}%>
</body>
</html>