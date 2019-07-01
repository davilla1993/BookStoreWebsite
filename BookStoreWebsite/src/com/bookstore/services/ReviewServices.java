package com.bookstore.services;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.dao.BookDao;
import com.bookstore.dao.ReviewDao;
import com.bookstore.entities.Book;
import com.bookstore.entities.Customer;
import com.bookstore.entities.Review;

public class ReviewServices {
	private ReviewDao reviewDao;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public ReviewServices(HttpServletRequest request, HttpServletResponse response) {
		super();

		this.request = request;
		this.response = response;
		this.reviewDao = new ReviewDao();
	}

	public void listAllReview() throws ServletException, IOException {

		listAllReview(null);
	}

	public void listAllReview(String message) throws ServletException, IOException {
		List<Review> listReviews = reviewDao.listAll();

		request.setAttribute("listReviews", listReviews);

		if (message != null) {
			request.setAttribute("message", message);
		}

		String listPage = "review_list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(listPage);
		dispatcher.forward(request, response);

	}

	public void editReview() throws ServletException, IOException {
		Integer reviewId = Integer.parseInt(request.getParameter("id"));
		Review review = reviewDao.get(reviewId);

		request.setAttribute("review", review);

		String editPage = "review_form.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(editPage);
		dispatcher.forward(request, response);

	}

	public void updateReview() throws ServletException, IOException {
		Integer reviewId = Integer.parseInt(request.getParameter("reviewId"));
		String headline = request.getParameter("headline");
		String comment = request.getParameter("comment");

		Review review = reviewDao.get(reviewId);
		review.setHeadline(headline);
		review.setComment(comment);

		reviewDao.update(review);

		String message = "The review has been updated successfully.";

		listAllReview(message);

	}

	public void deleteReview() throws ServletException, IOException {
		Integer reviewId = Integer.parseInt(request.getParameter("id"));
		reviewDao.delete(reviewId);

		String message = "The review has been deleted successfully";

		listAllReview(message);
	}

	public void showReviewForm() throws ServletException, IOException {

		Integer bookId = Integer.parseInt(request.getParameter("book_id"));
		BookDao bookDao = new BookDao();
		Book book = bookDao.get(bookId);

		HttpSession session = request.getSession();
		session.setAttribute("book", book);

		Customer customer = (Customer) session.getAttribute("loggedCustomer");

		Review existReview = reviewDao.findByCustomerAndBook(customer.getCustomerId(), bookId);

		String targetPage = "frontend/review_form.jsp";

		if (existReview != null) {

			request.setAttribute("review", existReview);
			targetPage = "frontend/review_info.jsp";

		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(targetPage);
		dispatcher.forward(request, response);

	}

	public void submitReview() throws ServletException, IOException {
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		Integer rating = Integer.parseInt(request.getParameter("rating"));
		String headline = request.getParameter("headline");
		String comment = request.getParameter("comment");

		Review newReview = new Review();
		newReview.setHeadline(headline);
		newReview.setComment(comment);
		newReview.setRating(rating);

		Book book = new Book();
		book.setBookId(bookId);
		newReview.setBook(book);

		Customer customer = (Customer) request.getSession().getAttribute("loggedCustomer");
		newReview.setCustomer(customer);

		reviewDao.create(newReview);

		String messagePage = "frontend/review_done.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(messagePage);
		dispatcher.forward(request, response);

	}
}
