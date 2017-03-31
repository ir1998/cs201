<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Invest CS</title>
</head>
<script>
	function validate() {
		var xhttp = new XMLHttpRequest();
		xhttp.open("GET",
				"${pageContext.request.contextPath}/LoginServlet?username="
						+ document.getElementById("username").value
						+ "&username="
						+ document.getElementById("password").value
						+ "&password=",
				false);
		xhttp.send();
		if (xhttp.responseText.trim().length > 0) {
			document.getElementById("formerror").innerHTML = xhttp.responseText;
			return false;
		}
		return true;
	};
</script>
<body>
	<h1>Log in to your account</h1>

	<form name="myform" method="GET" action="google.com"
		onsubmit="return validate();">
		<div id="formerror"></div>
	    <div>Username</div>
			<input type="text" id="username" name="username"/>
	    <br>
	    <div>Password</div>
	    <input type="text" id="password" name="password"/>
	    <br>
		<input type="submit" value="Log In"/>	
  </form>
  <br>
	<a href="signup.jsp" id="createAccount">
		<input type="button" value="Create New Account">
	</a>

</body>
</html>