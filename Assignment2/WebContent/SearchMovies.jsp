<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="data.Movie,java.util.Set" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="main.css">
</head>
<body bgcolor="#E6E6FA">
<h1 id="Title1" >Cinemate</h1>
<h2 id="Title2" >Enter a movie to search</h2>
</head>
<body>
	<form id="form1" method="GET" action="UserSearchServlet">
		<div id="ButtonHolder">
			<input type="text" name="searchedUser">
			<input id="MyButton" type="submit" value="Submit" name="Submit">
		</div>
	</form>
<%	if(session.getAttribute("searchResults") != null){%>
		<% Set<Movie> movieResults = (Set<Movie>)session.getAttribute("movieResults"); %>
		<div id="ResultsBox">
			<h4 id="Title3">Search Results</h4>
			<% for(Movie m: movieResults){ 
				System.out.println(m.getTitle());%>
				<h4 id="Results"> <%out.print(m.getTitle());%> </h4>
			<%}
			session.setAttribute("error", null); %>	
		</div>
	<%}else if(session.getAttribute("firstSearch") != null){%>
			<div id="ResultsBox">
				<h4 id="Title3">No Search Results Found</h4>
			</div>
			<%}session.setAttribute("firstSearch", true);%>
</body>
</html>