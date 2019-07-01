<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<html>
		<head>
			<meta charset="ISO-8859-1">
			<title>Page Not Found Error </title>
	    </head>
		<body>
			<div align="center">
				<img src="${pageContext.request.contextPath}/images/BookstoreLogo.png"/>
			</div>
			
			<div align="center">
				<h2>Sorry, the requested page could not be found.</h2>	
			</div>
			
			<div align="center">
				<a href="javascript:history.go(-1);">Go Back</a>
			</div>
		</body>
	</html>