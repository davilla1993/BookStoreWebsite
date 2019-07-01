package com.bookstore.dao;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.dao.OrderDao;
import com.bookstore.entities.Book;
import com.bookstore.entities.BookOrder;
import com.bookstore.entities.Customer;
import com.bookstore.entities.OrderDetail;

public class OrderDaoTest {
	
	private static OrderDao orderDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		orderDao = new OrderDao();
	}
  
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		orderDao.close();
	}
	

	@Test
	public void testCreateBookOrder() {
		BookOrder order = new BookOrder();
		Customer customer = new Customer();
		customer.setCustomerId(4);
		
		order.setCustomer(customer);
		order.setRecipientName("Tommy Brown");
		order.setRecipientPhone("3213242333");
		order.setShippingAddress("123 South Street, New York, USA");
		
		Set<OrderDetail> orderDetails = new HashSet<>();
		OrderDetail orderDetail = new OrderDetail();
		
		Book book = new Book(2);
		orderDetail.setBook(book); 
	    orderDetail.setQuantity(2);
		orderDetail.setSubtotal(73.4f);
		orderDetail.setBookOrder(order);
		
		orderDetails.add(orderDetail);
		
		order.setOrderDetails(orderDetails);
		
		BookOrder savedOrder = orderDao.create(order);
		
		assertTrue(savedOrder != null && savedOrder.getOrderDetails().size() > 0);
		
	}
	
	@Test
	public void testGest() {
		Integer orderId = 4;
		BookOrder order = orderDao.get(orderId);
		
		assertEquals(1, order.getOrderDetails().size());
		
	}
	
	@Test
	public void testListAll() {
		List<BookOrder> listOrders = orderDao.listAll();
		
		for(BookOrder order : listOrders){
			System.out.println(order.getOrderId() + "-" + order.getCustomer().getFullname() + "-" + order.getTotal() + "-" + order.getStatus());
			
			for(OrderDetail detail : order.getOrderDetails()) {
				Book book = detail.getBook();
				int quantity = detail.getQuantity();
				float subtotal = detail.getSubtotal();
				System.out.println("\t" + book.getTitle() + "-" + quantity + "-" + subtotal);
			}
		}
		
		assertTrue(listOrders.size() > 0);
	}
	
	@Test
	public void testUpdateBookOrderShppingAddress() {
		Integer orderId = 4;
		BookOrder order = orderDao.get(orderId);
		order.setShippingAddress("Tokoin Protestant Rue Bekpo");
		orderDao.update(order);
		
		BookOrder updatedOrder = orderDao.get(orderId);
		
		assertEquals(order.getShippingAddress(), updatedOrder.getShippingAddress());
	}
	
	@Test
	public void testCount() {
		long totalOrders = orderDao.count();
		assertEquals(4, totalOrders);
	}
	
	@Test
	public void testDeleteOrder() {
		int orderId = 5;
		orderDao.delete(orderId);
		
		BookOrder order = orderDao.get(orderId);
		
		assertNull(order);
	}
	
	@Test
	public void testListByCustomerNoOrders() {
		Integer customerId = 99;
		List<BookOrder> listOrders = orderDao.listByCustomer(customerId);
		
		assertTrue(listOrders.isEmpty());
	}
	
	@Test
	public void testListByCustomerHaveOrders() {
		Integer customerId = 4;
		List<BookOrder> listOrders = orderDao.listByCustomer(customerId);
		
		assertTrue(listOrders.size() > 0);
	}

}
