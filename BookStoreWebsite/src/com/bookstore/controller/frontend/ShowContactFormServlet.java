package com.bookstore.controller.frontend;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/view_contact_form")
public class ShowContactFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ShowContactFormServlet() {  
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String contactPage = "frontend/contact.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(contactPage);
		requestDispatcher.forward(request, response);
	}

}
