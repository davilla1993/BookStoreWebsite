package com.bookstore.controller.frontend;

import java.io.IOException;

import javax.ejb.EJB;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.services.ContactServices;

@WebServlet("/send_email")
public class sendEmailServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
     
	@EJB
	private ContactServices mail;
   
    public sendEmailServlet() {
        super();
        
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String fromEmail = request.getParameter("from");
		String subject = request.getParameter("subject");
		String message = request.getParameter("body");
		
		String username = "carlogbossou93@gmail.com";
		String password = "wxctfiyarfgisarh";
		String toEmail = "carlogbossou93@gmail.com";
		
		try {
			ContactServices.sendEmail(fromEmail, username, password, toEmail, subject, message);
		} catch (MessagingException e) {
			System.out.println("Couldn't send the mail" + e.getMessage());
			e.printStackTrace();
		}
		
	}
}

