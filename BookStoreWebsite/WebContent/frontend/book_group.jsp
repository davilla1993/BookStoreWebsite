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