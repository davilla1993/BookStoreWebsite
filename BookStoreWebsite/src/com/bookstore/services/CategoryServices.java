package com.bookstore.services;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDao;
import com.bookstore.dao.CategoryDao;
import com.bookstore.entities.Category;

public class CategoryServices {
	private CategoryDao categoryDao;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public CategoryServices(HttpServletRequest request, HttpServletResponse response) {

		this.request = request;
		this.response = response;
		categoryDao = new CategoryDao();

	}

	public void listCategory(String message) throws ServletException, IOException {
		List<Category> listCategory = categoryDao.listAll();

		request.setAttribute("listCategory", listCategory);

		if (message != null) {
			request.setAttribute("message", message);
		}

		String listPage = "category_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
	}

	public void listCategory() throws ServletException, IOException {

		listCategory(null);
	}

	public void createCategory() throws ServletException, IOException {

		String name = request.getParameter("name");
		Category existCategory = categoryDao.findByName(name);

		if (existCategory != null) {
			String message = "Could not create this category. A category with name " + name + " " + "already exists.";
			request.setAttribute("message", message);

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);

		} else {

			Category newCategory = new Category(name);
			categoryDao.create(newCategory);
			String message = "New Category created successfully";
			listCategory(message);

		}

	}

	public void editCategory() throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("id"));
		Category category = categoryDao.get(categoryId);
		request.setAttribute("category", category);

		String editPage = "category_form.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(editPage);
		requestDispatcher.forward(request, response);
	}

	public void updateCategory() throws ServletException, IOException {

		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		String categoryName = request.getParameter("name");

		Category categoryById = categoryDao.get(categoryId);
		Category categoryByName = categoryDao.findByName(categoryName);

		if (categoryByName != null && categoryById.getCategoryId() != categoryByName.getCategoryId()) {
			String message = "Could not update category. A category with name " + categoryName + " " +"already exists.";

			request.setAttribute("message", message);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
		} else {
			categoryById.setName(categoryName);
			categoryDao.update(categoryById);
			String message = "Category has been updated successfully";
			listCategory(message);
		}
	}
	
	public void deleteCategory() throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("id"));
		BookDao bookDao = new BookDao();
		long numberOfBooks = bookDao.countByCategory(categoryId);
		String message;
		
		if(numberOfBooks > 0) {
			
			message = "Couldn't delete the category (ID: %d) because it currently contains some books.";
			 message = String.format(message, numberOfBooks);
		}else {
			
		categoryDao.delete(categoryId);
		message = "The category with ID" + " " +categoryId + " " + "has been removed successfully.";
		}
		
		listCategory(message);
	}
	
}