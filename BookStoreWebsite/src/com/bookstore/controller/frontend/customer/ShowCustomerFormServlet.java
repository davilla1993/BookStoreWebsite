package com.bookstore.controller.frontend.customer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/register")
public class ShowCustomerFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ShowCustomerFormServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String registerFrom = "frontend/register_form.jsp";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(registerFrom);
		dispatcher.forward(request, response);
		
	}

}
