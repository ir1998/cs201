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
	<form action="MovieMenuServlet" id="Title4">
		<input type="submit" name="searchByActor" value="1. Search by Actor">
		<input type="submit" name="searchByTitle" value="2. Search by Title">
		<input type="submit" name="searchByGenre" value="3. Search by Genre">
		<input type="submit" name="mainMenu" value="4. Back to Main Menu">
	</form>
</body>
</html>
