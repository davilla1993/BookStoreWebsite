package com.bookstore.controller.frontend;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bookstore.dao.BookDao;
import com.bookstore.dao.CategoryDao;
import com.bookstore.entities.Book;

@WebServlet("")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public HomeServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		CategoryDao categoryDao = new CategoryDao();
		BookDao bookDao = new BookDao();
		
		List<Book> listNewBooks = bookDao.listNewBooks();
		
		request.setAttribute("listNewBooks", listNewBooks);

		  
		String homepage = "frontend/index.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(homepage);
		dispatcher.forward(request, response);
		
	}

	

}
