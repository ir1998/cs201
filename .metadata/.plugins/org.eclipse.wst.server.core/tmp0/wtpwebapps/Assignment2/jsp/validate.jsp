<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String fullname = request.getParameter("fullname");
	String username = request.getParameter("username");
	System.out.println(fullname + "." + username);
	if (fullname == null || fullname.length() == 0) {%>
		<font color="red"><strong>Full name needs a value.</strong></font><br />
	<% }
	if (username == null || username.length() == 0) {%>
		<font color="red"><strong>Username needs a value.</strong></font><br />
	<%}%>