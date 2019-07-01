<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
	<html>
		<head>
			<meta charset="ISO-8859-1">
			<title>Evergreen Books - Online Book Store</title>
			<link rel="stylesheet" href="css/style.css"/>
			
			<script type="text/javascript" src="js/jquery-3.4.0.min.js" ></script>
			<script type="text/javascript" src="js/jquery.validate.min.js" ></script>
		</head>
		<body>
			<jsp:directive.include file="header.jsp" />
			
			<div align="center">
				<h3>${message}</h3>
			</div>
			<jsp:directive.include file="footer.jsp" />
		</body>
	</html>