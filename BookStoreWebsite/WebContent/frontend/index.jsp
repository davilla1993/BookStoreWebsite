<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Evergreen Books - Online Books Store</title>
<link rel="stylesheet" href="css/style.css" />
</head>
<body>

	<jsp:directive.include file="header.jsp" />

	<div class="center">
		<br /> <br />
		<div>
			<h2>New Books:</h2>
			<c:forEach items="${listNewBooks}" var="book">
			
				<jsp:directive.include file="book_group.jsp" />
				
			</c:forEach>
		</div>
		<div class="next-row">
			<h2>Best-Selling Books:</h2>
			
			<c:forEach items="${listBestSellingBooks}" var="book">
			
				<jsp:directive.include file="book_group.jsp" />
				
			</c:forEach>
		</div>

		<div class="next-row">
		
			<h2>Most-favored Books:</h2>
			
			<c:forEach items="${listFavoredBooks}" var="book">
			
				<jsp:directive.include file="book_group.jsp" />
				
			</c:forEach>
		</div>
	</div>
	<br />
	<br />

	<jsp:directive.include file="footer.jsp" />
</body>
</html>