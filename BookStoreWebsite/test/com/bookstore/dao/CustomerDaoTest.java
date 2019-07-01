package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entities.Customer;

public class CustomerDaoTest {
	
	private static CustomerDao customerDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		customerDao = new CustomerDao();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		customerDao.close();
	}

	@Test
	public void testCreateCustomer() {
		Customer customer = new Customer();
		customer.setEmail("udinov@gmail.com");
		customer.setFullname("Alexandra Udinov");
		customer.setCity("Moscow");
		customer.setCountry("Russia");
		customer.setAddress("315 Peter Street");
		customer.setPassword("secret001");
		customer.setPhone("232233322112");
		customer.setZipcode("12000");
		
		Customer savedCustomer = customerDao.create(customer);
		assertTrue(savedCustomer.getCustomerId() > 0);
	}

	@Test
	public void testGet() {
		Integer customerId = 1; 
		Customer customer = customerDao.get(customerId);
	}
	
	@Test
	public void testUpdateCustomer() {
		Customer customer = customerDao.get(1);
		String fullName = "Tommy Brown";
		customer.setFullname(fullName);
		Customer updateCustomer = customerDao.update(customer);
		
		assertTrue(updateCustomer.getFullname().equals(fullName));
	}

	@Test
	public void testDeleteCustomer() {
		Integer customerId = 1;
		customerDao.delete(customerId);
		Customer customer = customerDao.get(1);
		
		assertNull(customer);
		
	}
	
	@Test
	public void testListAll() {
		List<Customer> listCustomers = customerDao.listAll();
		
		 assertFalse(listCustomers.isEmpty());
		 
		 for( Customer customer : listCustomers) {
			 
			 System.out.println(customer.getFullname());
		 }
		
	}
	
	@Test
	public void testCount() {
		long totalCustomers = customerDao.count();
		
		assertEquals(2, totalCustomers);
	}

	@Test
	public void testFindByEmail() {
		String email = "tom@gmail.com";
		Customer customer = customerDao.findByEmail(email);
		
		assertNotNull(customer);
	}
}
