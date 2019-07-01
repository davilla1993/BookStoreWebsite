package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entities.Book;
import com.bookstore.entities.Customer;
import com.bookstore.entities.Review;

public class ReviewDaoTest {
	
	private static ReviewDao reviewDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		reviewDao = new ReviewDao();	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		reviewDao.close();
	}
	
	@Test
	public void testCreateReview() {
		Review review = new Review();
		Book book = new Book();
		book.setBookId(12);
		
		Customer customer = new Customer();
		customer.setCustomerId(7);
		
		review.setBook(book);
		review.setCustomer(customer);
		
		review.setHeadline("This is a very good book !");
		review.setRating(5);
		review.setComment("I have just read this book. Very good.");
		
		Review savedReview = reviewDao.create(review);
		
		assertTrue(savedReview.getReviewId() > 0);
	}

	@Test
	public void testUpdateReview() {
		Integer reviewId = 1;
		Review review = reviewDao.get(reviewId);
		review.setHeadline("Excellent book");
		
		Review updatedReview = reviewDao.update(review);
		
	}

	@Test
	public void testGet() {
		Integer reviewId = 1;
		Review review = reviewDao.get(reviewId);
		assertNotNull(review);
	}

	@Test
	public void testDeleteObject() {
	}

	@Test
	public void testListAll() {
		List<Review> listReview = reviewDao.listAll();
		
		assertTrue(listReview.size() > 0);
	}

	@Test
	public void testCount() {
		long totalReviews = reviewDao.count();
		
		assertTrue(totalReviews > 0);
	}
	
	@Test
	public void testFindByCustomerAndBookNotFound() {
		Integer customerId = 100;
		Integer bookId = 99;	
		
		Review result = reviewDao.findByCustomerAndBook(customerId, bookId);
		
		assertNull(result);
		}
	
	@Test
	public void testFindByCustomerAndBookFound() {
		Integer customerId = 13;
		Integer bookId = 11;	
		
		Review result = reviewDao.findByCustomerAndBook(customerId, bookId);
		
		assertNotNull(result);
		}
	
}
