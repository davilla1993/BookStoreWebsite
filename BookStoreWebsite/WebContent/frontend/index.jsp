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
				<div class="book">
					<div>
						<a href="view_book?id=${book.bookId}"> <img
							src="data:image/jpg;base64,${book.base64Image}" width=80px;
							height=100px; />
						</a>
					</div>
					<div>
						<a href="view_book?id=${book.bookId}"> ${book.title} </a>
					</div>
					<div>
						<jsp:directive.include file="book_rating.jsp" />
					</div>
					<div>
						<i>by ${book.author}</i>
					</div>
					<div>${book.price}</div>
				</div>
			</c:forEach>
		</div>
		<div class="next-row">
			<h2>Best-Selling Books:</h2>
		</div>

		<div class="next-row">
			<h2>Most-favored Books:</h2>
		</div>
	</div>
	<br />
	<br />

	<jsp:directive.include file="footer.jsp" />
</body>
</html>