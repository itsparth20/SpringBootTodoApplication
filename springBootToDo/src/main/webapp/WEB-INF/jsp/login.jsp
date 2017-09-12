<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<div class=container>
	<form method="post">
		<font color="red">${inValidUser}</font><br /> Username: <input
			type="text" name="username"> Password: <input type="password"
			name="password"> <input type="submit">
	</form>
</div>
<%@ include file="common/footer.jspf"%>