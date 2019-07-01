package com.bookstore.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entities.Users;

public class UserDaoTest{
	
	private static UserDao userDao;


	@BeforeClass
	public static void setUpClass() {
	        userDao = new UserDao();
	        
	}

	@Test
	public void testCreateUsers() {
		Users user1 = new Users();
		user1.setEmail("tommy@gmail.com");
		user1.setFullName("Tommy Timothy");
		user1.setPassword("abcdefghij");

		user1 = userDao.create(user1);

		assertTrue(user1.getUserId() > 0);
	}

	@Test(expected = PersistenceException.class)
	public void testCreateUsersFieldsNotSet() {

		Users user1 = new Users();

		user1 = userDao.create(user1);

	}
	
	@Test
	public void testUpdateUsers() {
		Users user = new Users();
		user.setUserId(5);
		user.setEmail("nam@codejava.net");
		user.setFullName("Nam Ha Minh");
		user.setPassword("mysecret");
		
		user = userDao.update(user);
		String expected = "mysecret";
		String actual = user.getPassword();
		
		assertEquals(expected, actual);
			
	}
	 
	@Test
	public void testGetUsersFound() {
		Integer userId = 1;
		Users user = userDao.get(userId);
		
		if(user != null) {
			System.out.println(user.getEmail());
		}
		System.out.println(user.getEmail());
		assertNotNull(user);
		
	}
	
	@Test
	public void testGetUsersNotFound() {
		Integer userId = 99;
		Users user = userDao.get(userId);
		
		assertNull(user);
	}
	
	@Test
	public void testDeleteUsers() {
		Integer userId = 5;
		userDao.delete(userId);
		
		Users user = userDao.get(userId);
		
		assertNull(user);
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testDeleteNotExistUsers() {
		
		Integer userId = 55;
		userDao.delete(userId);
	}
	
	@Test
	public void testListAll() {
		
		List<Users> listUsers = userDao.listAll();
		
		for(Users user : listUsers) {
			
			System.out.println(user.getFullName() + " " + user.getEmail());
		}
		assertTrue(listUsers.size() > 0);
	}
	
	@Test
	public void testCount() {
		long totalUsers = userDao.count();
		
		assertEquals(4, totalUsers);
	}
	
	@Test
	public void testFindByEmail() { 
		
		String email = "carlogbossou93@gmail.com";
		Users user = userDao.findByEmail(email);
		
		assertNotNull(user);
	}
	
	@Test
	public void testCheckLoginFailed() {
		String email = "alexandraDelgado@gmail.com";
		String password = "123456789";
		boolean loginResult = userDao.checkLogin(email, password);
		
	}

	@AfterClass
	public static void tearDownClass() {
		userDao.close();
	}

}
