<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="data.DataStorage" %>
<%@ page import="data.User" %>
<%@ page import="data.StringConstants" %>
<%@ page import="java.util.List" %>
<html>
<!-- get the data storage object and user info -->
<%  DataStorage ds = (DataStorage) session.getAttribute(StringConstants.DATA); 
	User user = ds.getLoggedInUser();
	boolean loggedInUser = false;
	boolean following = false;
	//if profile view is set, view different user's profile
	if(request.getParameter("profileView")!= null){
		session.setAttribute("profileView", request.getParameter("profileView"));
		if(!user.getUsername().equals(request.getParameter("profileView"))){
			user = ds.getUser((String)request.getParameter("profileView"));
		}
	}
	if(user.getUsername().equals(ds.getLoggedInUser().getUsername()))
		loggedInUser = true;
	else{
		following = ds.getLoggedInUser().isFollowing(user.getUsername());
	}
	
	List<data.Event> eventFeed = user.getFeed();
	String name = user.getFName() +" "+user.getLName();
	String un = "@"+user.getUsername();

	
%>
<head>
	<title>User Profile</title>
	<link rel="stylesheet" href="../css/main.css">
	<link rel="stylesheet" href="../css/profile.css">
	<link href="https://fonts.googleapis.com/css?family=Lato:700i" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="../../../../../bootstrap-3.3.7-dist/*">
	<script type="text/javascript">

		function followToggle(){
			var xhttp = new XMLHttpRequest();
			xhttp.open("GET", "${pageContext.request.contextPath}/FollowToggleServlet?toggle="+document.getElementById("follow-toggle").innerHTML, false);
			xhttp.send();
			if (xhttp.responseText.trim().length > 0) {
				document.getElementById("follow-toggle").innerHTML = xhttp.responseText;
			}
		}
	</script>
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

	<div id = "user_container_outer">
		<div id = "user_container_inner">
			<img src = <%=user.getImage()%> alt = "Profile Image Not Found">
			<h1 style="font-size:30px;"><%=name %></h1>
			<h3><%=un %> 
			</h3>
			<%if(!loggedInUser){ %>
				<button type="button" onclick="followToggle();" 
				style="float:right; display:block; width:80px; height:20px; margin:10px;" 
				id="follow-toggle"><%if(!loggedInUser && !following){ out.print("Follow");}
				else if(!loggedInUser){out.print("Unfollow");} %></button>
			<%} %>
		</div>

	</div>
<!-- display followers and following if there are any -->
<br><br><br>
	<div id="follow_container" style="float:left;">
		<h1>Followers</h1>
		<% for (String username : user.getFollowers()) { %>
			<h2><a data-toggle="tooltip" title="Visit profile" href="profile.jsp?profileView=<%=username%>"><%=username%></a></h2>
		<% } %>
	</div>
	<div id="follow_container" >
		<h1>Feed</h1>
		<%
		for (data.Event event : eventFeed){
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
			session.setAttribute("ActionImage", actionImage);%>
				<div style="display:block;">
					<a><img style="max-height:50px; margin:10px; vertical-align:middle;" src="<%out.print(session.getAttribute("ActionImage"));%>">
					<%out.print(event.getAction().toLowerCase());%></a>
					<a href="movie_page.jsp?movieView=<%out.print(event.getMovie().getTitle());%>"></a>
					<a style="max-height:100px; margin:10px;"><%out.print(event.getMovie().getTitle()); %></a>
				</div>
		<% }%>
	</div>
	<div id="follow_container" style="float:right;">
		<h1>Following</h1>
		<% for (String username : user.getFollowing()) { %>
			<h2><a data-toggle="tooltip" title="Visit profile" href = "profile.jsp?profileView=<%=username%>" ><%=username %></a></h2>
		<%} %>
	</div>

</body>
</html>