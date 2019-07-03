package com.bookstore.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entities.Book;
import com.bookstore.entities.Category;

public class BookDaoTest {
	
	private static BookDao bookDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bookDao = new BookDao();
	}


	@Test
	public void testCreateBook() throws ParseException, IOException {
		Book newBook = new Book();
		
		Category category = new Category("Java Core");
		category.setCategoryId(1);
		
		newBook.setCategory(category);
		newBook.setTitle("Effective Java (2nd Edition)");
		newBook.setAuthor("Joshua Bloch");
		newBook.setDescription("New coverage of generics, enums, annotations, autoboxing, the for-each loop, varargs, concurrency utilities, and much more");
		newBook.setPrice(38.87f);
		newBook.setIsbn("0321356683");
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("05/28/2008");
		newBook.setPublishDate(publishDate);
		
		
		String imagePath ="I:\\Eclipse-workspace\\bookstoreproject\\dummy-data-for-books\\EffectiveJava.jpg";
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		newBook.setImage(imageBytes);
		
		Book createBook = bookDao.create(newBook);
		
		assertTrue(createBook.getBookId() > 0);
	}
	
	@Test
	public void testUpdateBook() throws ParseException, IOException {
		Book existBook = new Book();
		existBook.setBookId(2);
		
		Category category = new Category("Java EE");
		category.setCategoryId(2);
		
		existBook.setCategory(category);
		existBook.setTitle("Java 8 in Action");
		existBook.setAuthor("Raoul-Gabriel Urma, Mario Fusco, Alan Mycroft");
		existBook.setDescription("Java 8 in Action is a clearly written guide to the new features of Java 8");
		existBook.setPrice(36.72f);
		existBook.setIsbn("1617291994");
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("08/28/2014");
		existBook.setPublishDate(publishDate);
		
		
		String imagePath ="I:\\Eclipse-workspace\\bookstoreproject\\dummy-data-for-books\\Java8inAction.jpg";
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		existBook.setImage(imageBytes);
		
		Book updateBook = bookDao.update(existBook);
		
		
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testDeleteBookFail() {
		Integer bookId = 100;
		bookDao.delete(bookId);

	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testDeleteBookSucess() {
		Integer bookId = 4;
		bookDao.delete(bookId);
		
		assertTrue(true);
	}
	
	@Test
	public void testGetBookSuccess() {
		Integer bookId = 2;
		Book book = bookDao.get(bookId);
		
		assertNotNull(book);
	}
	
	@Test
	public void testListAll() {
		List<Book> listBooks = bookDao.listAll();
		
		for(Book aBook : listBooks) {
			
			System.out.println(aBook.getTitle() + "-" + aBook.getAuthor());
		}
		assertFalse(listBooks.isEmpty());
	}
	
	
	@Test
	public void testFindTitleNotExist() {
		String title = "Thinkin in Java";
		Book book = bookDao.findByTitle(title);
		
		assertNull(book);
	}
	
	@Test
	public void testFindTitleExist() {
		String title = "Java 8 in Action";
		Book book = bookDao.findByTitle(title);
		
		assertNull(book);
	}
	
	@Test
	public void testCount() {
		long totalBooks = bookDao.count();
		
		assertEquals(2, totalBooks);
	}
	
	@Test
	public void testListByCategory() {
		int categoryId = 1;
		
		List<Book> listBooks = bookDao.listByCategory(categoryId);
		
		assertTrue(listBooks.size() > 0);

	}
	
	@Test
	public void testCountByCategory() {
		int categoryId = 4;
		long numOfBooks = bookDao.countByCategory(categoryId);
	}
	

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
			bookDao.close();
	}

	@Test
	public void testListBestSellingBooks() {
		List<Book> topBestSellingBooks = bookDao.listBestSellingBooks();
		
		for(Book book : topBestSellingBooks) {
			System.out.println(book.getTitle());
		}
		
		assertEquals(4, topBestSellingBooks.size());
	}
	
	@Test
	public void testListMostFavoredBooks() {
		List<Book> topFavoredBooks = bookDao.listMostFavoredBooks();
		
		for(Book book : topFavoredBooks) {
			System.out.println(book.getTitle());
		}
		
		assertEquals(3, topFavoredBooks.size());
	}
	

}
