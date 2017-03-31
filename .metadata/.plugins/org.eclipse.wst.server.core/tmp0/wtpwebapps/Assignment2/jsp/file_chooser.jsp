<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="data.StringConstants" %>
<html>
<head>
<title>File Chooser</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
<link href="https://fonts.googleapis.com/css?family=Lato:700i"
	rel="stylesheet">
</head>
<script>
	function validate()
	{
		var xhttp = new XMLHttpRequest();
		xhttp.open("GET", "${pageContext.request.contextPath}/FileChooserServlet?infile=" + document.getElementById("infile").value, false);
		xhttp.send();
		if (xhttp.responseText.trim().length > 0) {
			document.getElementById("formerror").innerHTML = xhttp.responseText;
			return false;
		}else{
			return true;
		}
<%-- 		window.location.href = "${pageContext.request.contextPath}<%= StringConstants.FILE_CHOOSER_SERVLET%>infile=" + document.infile.value;
 --%>		
	}
</script>
<body>
	<div id="title_container">Cinemate</div>

	<div id="welcome_text">
		Welcome to Cinemate, a Movie Social Media Medium.
		<br>
		Please input a file so that you may begin your experience.
	</div>
	<div id="outer_container">
		<div id="inner_container">
			<form name="myform" method="GET" onsubmit="return validate();" action="<%= StringConstants.LOGIN_JSP%>">
				<input style = "width: 72%; margin-right: 5px;" type="text" id="infile" name="<%= StringConstants.INFILE%>">
				<input style = "width: 20%;" type="submit" name="submit">
 				<!-- print error using AJAX HERE -->
				<div id="formerror"></div>
			</form>

		</div>
	</div>
</body>
</html>