<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
	<html>
		<head>
		<meta charset="ISO-8859-1">
		<title>Admin Login</title>
		<link rel="stylesheet" type="text/css" href="../css/style.css"/>
		<script type="text/javascript" src="../js/jquery-3.4.0.min.js" ></script>
		<script type="text/javascript" src="../js/jquery.validate.min.js" ></script>
			
	</head>
	<body>
		<div align="center">
			<h1>Book Store Administration</h1>
			<h2>Admin Login</h2>
			
			<c:if test="${message != null }">
			
				<div>
					<h4> ${message} </h4>
				</div>
			</c:if>
			
			<form id="loginForm" action="login" method="post" >
				<table>
					<tr>
						<td>Email:</td>
						<td><input type="text" name="email" id="email" size="20"/></td>
					</tr>
					<tr>
						<td>Password:</td>
						<td><input type="password" name="password" id="password" size="20"/></td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<button type="submit">Login</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
	
	<script type="text/javascript">

			$(document).ready(function(){
				
				$("#loginForm").validate({
					rules: {
						email: {

							required: true,
							email: true
							},
						
						password: "required",
						},

						messages:{
							email: {
								required:"Please, enter email",
								email: "Please, enter a valid address email"
								},
							
							password : "Please, enter password"
						 
							}
					});
				});
	
		</script>
</html>