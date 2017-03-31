<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="data.StringConstants" %>
<html>
<head>
	<title>Login</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
	<link href="https://fonts.googleapis.com/css?family=Lato:700i" rel="stylesheet">
</head>
<script>
	function validate()
	{
		var xhttp = new XMLHttpRequest();
		xhttp.open("GET", "${pageContext.request.contextPath}<%=StringConstants.LOGIN_SERVLET%>?username="
				+ document.getElementById("username").value +"&password=" + document.getElementById("password").value, false);
		xhttp.send();
		if (xhttp.responseText.trim().length > 0) {
			document.getElementById("formerror").innerHTML = xhttp.responseText;
			return false;
		}
		return true;
	}
</script>
<body>

	<div id = "title_container">
		Cinemate
	</div>

	<div id = "welcome_text">
		File parsed! Please log in.
	</div>

	<!-- <form action="demo_form.asp"> -->
	<div id = "outer_container">
		<div id = "inner_container">
			<div id = "login_container">
				<form name="myform" method="GET" onsubmit="return validate();" action="<%=StringConstants.FEED_JSP%>">
				<!--  onsubmit="return validate();"-->
					Username
					<br>
					<input type="text" id="username" name="<%= StringConstants.USERNAME%>">
					<br>
					Password
					<br>
					<input type="text" id="password" name="<%= StringConstants.PASSWORD%>">
					<br>
					<div id="formerror"></div>
					<br>
					<input style = "margin-left: 5px;" type="submit" value="Log In">
				</form> 
				<form action = "signup.jsp">
					<input style = "margin-left: 5px;" type="submit" value="Sign Up">
				</form>
			</div>
		</div>
	</div>
</body>
</html>