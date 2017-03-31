<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.Set" %>
<%@ page import="data.User" %>
<%@ page import="data.Movie" %>
<%@ page import="data.StringConstants" %>
<html>

<%
	//get the search parameter passed through the href
	String searchParam = (String) request.getParameter(StringConstants.SEARCH_PARAM);
	//the string we will display in the page description
	String toDisplay = searchParam;
	//if the search parameter is not null, we know we came from the href, and we should set the search type
	//as an attribute of the session
	if (searchParam != null) {
		session.setAttribute(StringConstants.SEARCH, searchParam);
	}
	//if the search parameter is null, we know we came from the servlet, and not the href
	//so we know that the search type has been stored in the session, and we need to display that instead
	else{
		toDisplay = (String) session.getAttribute(StringConstants.SEARCH);
	}
	//get the boolean of whether the search is for users or movies
	Boolean isUserSearch = (Boolean) request.getAttribute(StringConstants.IS_USER_SEARCH);
	//if the boolean is null, we don't have results because we must have come from the href
	//if the boolean is not null, we came from the servlet so we must have results (even if there are 0 results)
	Boolean haveResults = (isUserSearch != null);
%>
<head>
	<title>Search</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
	<link rel="stylesheet" href= "${pageContext.request.contextPath}/css/search.css">

	<link href="https://fonts.googleapis.com/css?family=Lato:700i" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="../bootstrap-3.3.7-dist/*">
</head>
<body>
<div>
		<ul>
			<li><a data-toggle="tooltip" title="Feed" href="feed.jsp">Feed</a></li>
			<li><a data-toggle="tooltip" title="Profile" href="profile.jsp">Profile</a></li>
			<li style="border-right:none;">
				<form action = "${pageContext.request.contextPath}<%= StringConstants.SEARCH_SERVLET%>">
                            <div class="input-group" id=<%= StringConstants.SEARCH %>  style="width: 100%">
                                <input style = "width: 40%; margin-left:20px; margin-top:8px;" type="text" size = "40" name="<%= StringConstants.SEARCH_PARAM %>">
								<select data-toggle="tooltip" title="Select search type" style="width:15%;" name="selectSearch">
									<option value="movies">Movies</option>
									<option value="users">Users</option>
								</select>
								<input data-toggle="tooltip" title="Search" style = "width: 15%; margin-left: 5px;" type="submit" value="Search">
                            </div>
                        </form>
			</li>
			
			<li data-toggle="tooltip" title="Exit and choose new file" style="float:right"><a href="file_chooser.jsp">Exit</a></li>
			<li data-toggle="tooltip" title="Logout" style="border-right: 1px solid #bbb; float:right"><a href="login.jsp">Logout</a></li>
		</ul>
	</div>
	<div id = "title_container">
		Cinemate
	</div>

	<div id = "welcome_text">
		You searched for "<%= toDisplay %>"
	</div>

	<div id = "outer_container">
		<div id = "inner_container">
			<%-- <form action = "${pageContext.request.contextPath}<%= StringConstants.SEARCH_SERVLET%>">
				<input style = "width: 72%;" type="text" size = "40" name="<%= StringConstants.SEARCH_PARAM %>">
				<input style = "width: 20%; margin-left: 5px;" type="submit" value="Search">
			</form> --%>

			<br>
			<div id = "search">
				<table>
					<thead >
						<tr>
							<td>Search Results</td>
						</tr>
					</thead>
						<tbody>
						<!-- if we have results -->
							<% if (haveResults){ %>
							<!-- if it was a user search, we know the results will be a set of Users -->
								<% if (isUserSearch){
									Set<User> results = (Set<User>)request.getAttribute(StringConstants.RESULTS);
								
									for (User result : results){ %>
										<tr><td><a data-toggle="tooltip" title="Visit profile" href="jsp/profile.jsp?profileView=<%= result.getUsername()%>"  ><%= result.getUsername()%></a></td></tr>
								
									<% } 
								}
							/* otherwise we know the results will be a set of Movies */
								else{
								
									Set<Movie> results = (Set<Movie>)request.getAttribute(StringConstants.RESULTS);
								
									if (results != null){
									
										for (Movie result : results){ %>
											<tr><td><a data-toggle="tooltip" title="See movie info" href = "jsp/movie_page.jsp?movieView=<%= result.getTitle()%>"><%= result.getTitle()%></a></td></tr>
									<% } 
									}
								} %>
						<% } %>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>