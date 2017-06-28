<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="data.Event" %>
<%@ page import="data.MySQLDriver" %>
<%@ include file="navbar.jsp" %>
<% sql = (MySQLDriver)session.getAttribute("sql");%>
<html>
<head>
	<title>Feed</title>
	<link rel="stylesheet" href="../css/main.css">
	<link rel="stylesheet" href="../css/big_feed.css">
	<link href="https://fonts.googleapis.com/css?family=Lato:700i" rel="stylesheet">
</head>
<body>

	<div id = "big_feed_container">
		<table id = "big_feed">
			<tbody>
			<%
				Set<Event> events = new HashSet<>();
				sql = (MySQLDriver)session.getAttribute("sql");	
				User u = sql.getLoggedInUser();
				events.addAll(sql.getFeedByUsername(u.getUsername(), u.getFollowing()));

				for (Event event : events) {
					User user = sql.getUserByUsername(event.getUsername());
			%>
				<tr>
					<td>
					<a href = "${pageContext.request.contextPath}/jsp/user_profile.jsp?<%= StringConstants.USERNAME %>=<%=user.getUsername()%>">
						<img src = "<%= user.getImage() %>" class = "round clickable" alt = "Profile Image Not Found" title = "<%=user.getFullname() %>">
					</a>
					</td>
					<td><img src = "<%= StringConstants.ACTION_IMG_EXT+ event.getActionIcon() %>" title = "<%= event.getAction().toLowerCase() %>"></td>
					<td>
					<a href = "${pageContext.request.contextPath}/jsp/movie_profile.jsp?<%=StringConstants.TITLE%>=<%=event.getMovieTitle()%>">
					<img src = "<%= event.getMovie().getImage() %>" class = "clickable" title = "<%= event.getMovieTitle() %>">
					</a>
					</td>
					<td>
					<a><%=event.getTime()%></a>
					</td>
				</tr>
			<% } %>
			</tbody>
		</table>
	</div>

</body>
</html>