package com.bookstore.services;

import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.ejb.Stateless;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Stateless
public class ContactServices extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;

	Properties emailProperties;
	Session mailSession;
	MimeMessage emailMessage;

	String emailHost = "smtp.gmail.com";
	String emailPort = "587";
	String toEmail = "carlogbossou93@gmail.com";
	String password = "hfwbohkojdkbfrms";

	public ContactServices(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
	}

	public void setMailServerProperties() {

		emailProperties = System.getProperties();
		emailProperties.put("mail.smtp.host", emailHost);
		emailProperties.put("mail.smtp.auth", "true");
		emailProperties.put("mail.smtp.port", emailPort);
		emailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		emailProperties.put("mail.smtp.socketFactory.port", "465");
		emailProperties.put("mail.smtp.socketFactory.fallback", "false");
	}

	public void createEmailMessage(String emailSubject, String emailBody) throws AddressException, MessagingException {

		mailSession = Session.getDefaultInstance(emailProperties, null);
		mailSession.setDebug(true);
		emailMessage = new MimeMessage(mailSession);
		emailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
		emailMessage.setSubject(emailSubject);
		emailMessage.setContent(emailBody, "text/html");
	}

	public void sendEmail() throws MessagingException {
		Transport transport = mailSession.getTransport("smtp");
		transport = mailSession.getTransport("smtps");
		transport.connect(emailHost, toEmail, password);
		transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
		transport.close();
	}

	public void sendMail() throws  ServletException, IOException {
		
		this.setMailServerProperties();
		
		String messageError = " ";
		String messageSuccess = " ";
		String emailBody = " ";
		
		String submit = request.getParameter("submit");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String fromEmail = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String emailSubject = request.getParameter("title");
		String message = request.getParameter("message");
		
		if(submit != null) {
			
			if (firstname != null) {
				emailBody = emailBody + "Sender name :" + firstname + "<br/>";
				} 

			if (lastname != null) {
				emailBody = emailBody + "Sender lastname :" + lastname + "<br/>";
				}
			
			if (fromEmail != null && fromEmail.trim().length() != 0) {
				emailBody = emailBody + "Sender email :" + fromEmail  + "<br/>";			
			}
			
			if (telephone != null) {
				emailBody = emailBody + "Sender phone :" + telephone  + "<br/>";
			}
				
			if (message != null) {
				emailBody = emailBody +  "Message :" + message  + "<br/>";
			} 
			
			try {
				this.createEmailMessage(emailSubject, emailBody);
				this.sendEmail();
				messageSuccess= "Your email have been sent successfully. Thank You !!";
			} catch (MessagingException me) {
				me.getMessage();
				messageError = "Ouuups!! Something wrong occured. Please, verify your connection...";
			}
			
			request.setAttribute("messageError", messageError);
			request.setAttribute("messageSuccess", messageSuccess);
			
			String contactPage = "frontend/contact.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(contactPage);
			dispatcher.forward(request, response);
		}
		}
		}
			
			


