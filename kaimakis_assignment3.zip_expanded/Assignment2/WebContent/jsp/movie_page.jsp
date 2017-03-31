<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="data.DataStorage" %>
<%@ page import="data.Movie" %>
<%@ page import="data.Actor" %>
<%@ page import="data.StringConstants" %>
<%@ page import="java.util.List" %>
<html>
<!-- get the data storage object and user info -->
<%  DataStorage ds = (DataStorage) session.getAttribute(StringConstants.DATA); 

	//if movieView is something, show movie!
	Movie movie = null;
	if(request.getParameter("movieView")!= null){
		String movieTitle = request.getParameter("movieView");
		movie = ds.getMovieByTitle(movieTitle);
	}
	
%>
<head>
	<title>User Profile</title>
	<link rel="stylesheet" href="../css/main.css">
	<link rel="stylesheet" href="../css/profile.css">
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
	<div class="row panel">
        <div class="col-md-8  col-xs-12">
           <img data-toggle="tooltip" title="<%out.print(movie.getTitle()); %>" id="movie_image" style="float:left; margin:0px 30px" src="<%if(movie != null){out.print(movie.getImage());} %>" />
           <div  class="header">
                <h1><%if(movie != null){out.print(movie.getTitle());} %>(<%if(movie != null){out.print(movie.getYear());} %>)</h1>
                <h2>Genre: <%if(movie != null){out.print(movie.getGenre());} %></h2>
                <h2>Director: <%if(movie != null){out.print(movie.getDirector());} %></h2>
                <h2>Actors: <%
                if(movie != null){
                	String toPrint="";
                	for(Actor actor:movie.getActors()){
                		toPrint += actor.getName() +", ";
                	};
                	toPrint = toPrint.substring(0, toPrint.length()-2);
                	out.print(toPrint);
                } %></h2>
                <h2>Writers: <%
	                if(movie != null){
	                	String toPrint= "";
	                	for(String writer: movie.getWriters()){ 
	                		toPrint += writer + ", ";
	                	}
	                	toPrint = toPrint.substring(0,toPrint.length()-2);
	                	out.print(toPrint);
	                } %></h2>
                <h3>Average rating: <%if(movie != null){out.print(movie.getAvgRating());} %></h3>
               
                <span><%if(movie != null){out.print(movie.getDescription());} %></span>
           </div>
        </div>
        <div align="left" style="clear:both; margin:20px 20px;">
        	<!-- put like, dislike, movie rating here -->
        </div>
        
        <div align="center" style="clear:both; margin: 20px 20px;">
        <br>
        	<table>
	        	<thead style="float:center;">
	        		<tr><h1>Cast:</h1></tr>
	        	</thead>
	        	<tbody>
        	<%
                if(movie != null){
                	String toPrint="";
                	for(Actor actor:movie.getActors()){
                		%>
                		<tr style="margin:10px; display:block;">
                			<td><img style="float:center; margin: 0px 20px 10px 10px; max-height:250px; min-height:200px; vertical-align:middle;" 
                			src="<%out.print(actor.getImage());%>"><span style="font-size:30px;"><%out.print(actor.getName());%></span></td>
                		</tr>
                	<%}
                } %>
                </tbody>
        	</table>
        </div>
    </div>   
</body>
</html>