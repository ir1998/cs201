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
<body>
		<script>
			function validate()
			{
				var xhttp = new XMLHttpRequest();
				xhttp.open("GET", "${pageContext.request.contextPath}/SignupServlet?fullname=" + document.getElementById("fullname").value + 
						"&username=" + document.getElementById("username").value +"&password="+document.getElementById("password").value
						+"&imgurl="+document.getElementById("imgurl").value, false);
				xhttp.send();
				if (xhttp.responseText.trim().length > 0) {
					document.getElementById("formerror").innerHTML = xhttp.responseText;
					return false;
				}
				return true;
			};
		</script>

	<div id = "title_container">
		Cinemate
	</div>

	<div id = "welcome_text">
		Please enter your information to sign up.
	</div>

	<!-- <form action="demo_form.asp"> -->
	<div id = "outer_container">
		<div id = "inner_container">
			<div id = "login_container">
				<form name="myform" method="GET" action="login.jsp" onsubmit="return validate();">
					<br>
					<div id="formerror"></div>
					<input type="text" id="fullname" placeholder="Full Name">
					<br>
					<input type="text" id="username" placeholder="Username">
					<br>
					<input type="text" id="password" name="password" placeholder="Password">
					<br>
					<input type="text" id="imgurl" name="imgurl" placeholder="Image URL">
					<br><br>
					<input type="submit" name="submit" value="Sign Up">
				</form> 
			</div>
		</div>
	</div>
</body>
</html>