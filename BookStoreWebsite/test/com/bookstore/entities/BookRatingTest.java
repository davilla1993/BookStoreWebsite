package com.bookstore.entities;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class BookRatingTest {

	@Test
	public void testAverageRating(){
		Book book = new Book();
		
		Set<Review> reviews = new HashSet<>();
		Review review = new Review();
		review.setRating(5);
		reviews.add(review);
		
		book.setReviews(reviews);
		
		float averageRating = book.getAverageRating();
		
		assertEquals(5.0, averageRating, 0.0);
	}

	@Test
	public void testAverageRating2(){
		Book book = new Book();
		
		float averageRating = book.getAverageRating();
		
		assertEquals(0.0, averageRating, 0.0);
	}
	
	@Test
	public void testAverageRating3(){
		Book book = new Book();
		
		Set<Review> reviews = new HashSet<>();
		Review review1 = new Review();
		review1.setRating(5);
		reviews.add(review1);
		
		Review review2 = new Review();
		review1.setRating(4);
		reviews.add(review2);
		
		Review review3 = new Review();
		review1.setRating(3);
		reviews.add(review3);
		
		book.setReviews(reviews);
		
		float averageRating = book.getAverageRating();
		
		assertEquals(4.0, averageRating, 0.0);
	}
}
