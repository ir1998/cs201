<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="main.css">
</head>
<body bgcolor="#E6E6FA">
<h1 id="Title1" >Cinemate</h1>
<h2 id="Title2" >Logged in! What would you like to do?</h2>
</head>
<body>
	<form action="MainMenuServlet" id="Title4">
		<input type="submit" name="searchUsers" value="1. Search Users">
		<input type="submit" name="searchMovies" value="2. Search Movies">
		<input type="submit" name="viewFeed" value="3. View Feed">
		<input type="submit" name="viewProfile" value="4. View Profile">
		<input type="submit" name="logOut" value="5. Logout">
		<input type="submit" name="exit" value="6. Exit">
	</form>
</body>
</html>
