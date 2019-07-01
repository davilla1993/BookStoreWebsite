package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entities.Category;

public class CategoryDaoTest {
	
	private static CategoryDao categoryDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
				categoryDao = new CategoryDao();
	}


	@Test
	public void testCreateCategory() {
		Category newCat = new Category("Advanced Java");
		Category category = categoryDao.create(newCat);
		
		assertTrue(category != null && category.getCategoryId() > 0);
	}

	@Test
	public void testUpdateCategory() {
		
		Category cat = new Category("Java Core");
		cat.setCategoryId(1);
		
		Category category = categoryDao.update(cat);
		assertEquals(cat.getName(), category.getName());
		
	}

	@Test
	public void testGet() {
		Integer catId = 2;
		Category cat = categoryDao.get(catId);
		
		assertNotNull(cat);
	}

	@Test
	public void testDeleteObject() {
		Integer catId = 3;
		categoryDao.delete(catId);
		
		Category cat = categoryDao.get(catId);
		
		assertNotNull(cat);
		
	}

	@Test
	public void testListAll() {
		List<Category> listCategory = categoryDao.listAll();
		
		assertTrue(listCategory.size() > 0);
	}

	@Test
	public void testCount() {
		long totalCategories = categoryDao.count();
		
		assertEquals(2, totalCategories);
	}
	
	@Test
	public void testFindByName() {
		String name = "Java Core";
		Category category = categoryDao.findByName(name);
		
		assertNotNull(category);
	}
	@AfterClass
	public static void tearDownClass() {
		categoryDao.close();
	}

}
