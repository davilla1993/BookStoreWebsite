package com.bookstore.controller.frontend;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.services.ContactServices;

@WebServlet("/send_email")
public class sendEmailServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();
     
	@EJB
	private ContactServices mail;
   
    public sendEmailServlet() {
        super();
        
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ContactServices contactServices = new ContactServices(request, response);
		contactServices.readyEmail();
	}
	
}

