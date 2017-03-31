<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="data.DataStorage" %>
<%@ page import="data.User" %>
<%@ page import="data.Event" %>
<%@ page import="data.StringConstants" %>
<html>
<head>
	<title>User Profile</title>
	<link rel="stylesheet" href= "../css/main.css">
	<link rel="stylesheet" href= "../css/feed.css">
	<link rel="stylesheet" href= "../css/profile.css">
	<link href="https://fonts.googleapis.com/css?family=Lato:700i" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="../bootstrap-3.3.7-dist/*">
</head>
<body>
<!-- get the data storage object and logged in user info -->
<%  DataStorage ds = (DataStorage) session.getAttribute(StringConstants.DATA); 
	User user = ds.getLoggedInUser();
	String name = user.getFName() +" "+user.getLName();
	String un = "@"+user.getUsername();
%>
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
	<br>
		<table id = "feed">
			<thead >
				<tr>
					<td><h2>Feed</h2></td>
				</tr>
			</thead>
			<tbody>
<!-- 			Get the users list of following and add their own username. Then iterate through this new set and get the user object
			and iterate through each of their feeds -->
				<% 
				Set<String> following = new HashSet<>(user.getFollowing());
				following.add(user.getUsername());
				
				for (String username : following){
					
					User current = ds.getUser(username);
							
						for (Event event : current.getFeed()){
							User currentUser = ds.getUser(event.getUsername());
							String actionImage="";
							if(event.getAction().toLowerCase().equals("rated")){
								double rating = event.getRating();
								if(rating<3){
									actionImage="../img/actions/rating1.png";
								}else if(rating <5){
									actionImage="../img/actions/rating2.png";
								}else if(rating <7){
									actionImage="../img/actions/rating3.png";
								}else if(rating <9){
									actionImage="../img/actions/rating4.png";
								}else if(rating <=10){
									actionImage="../img/actions/rating5.png";
								}
							}else if(event.getAction().toLowerCase().equals("watched")){
								actionImage="../img/actions/watched.png";
							}else if(event.getAction().toLowerCase().equals("liked")){
								actionImage="../img/actions/liked.png";
							}else if(event.getAction().toLowerCase().equals("disliked")){
								actionImage="../img/actions/disliked.png";
							}
							session.setAttribute("ActionImage", actionImage);
							%><tr>
								<td>
									<a data-toggle="tooltip" title="<%out.print(event.getUsername());%>" 
									href="profile.jsp?profileView=<%out.print(event.getUsername());%>"><img   
									id="profile_image" src=<%out.print(currentUser.getImage());%> alt = "Profile Image Not Found"></a>
									<a data-toggle="tooltip" title="<%out.print(event.getAction().toLowerCase());%>" ><img id="action_image"src="<%out.print(session.getAttribute("ActionImage"));%>"></a>
									<a data-toggle="tooltip" title="<%out.print(event.getMovie().getTitle());%>" href="movie_page.jsp?movieView=<%out.print(event.getMovie().getTitle());%>"><img id="movie_image" 
									src="<%out.print(event.getMovie().getImage()); %>"></a>
								</td> 
							
							</tr>
							<%-- <%
							String toShow = event.getUsername() +" " + event.getAction().toLowerCase() +" "+event.getMovie().getTitle(); %>
									
							<tr><td> <%= toShow %></td></tr> --%>
					<% }
				} %>
			</tbody>
		</table>
</body>
</html>