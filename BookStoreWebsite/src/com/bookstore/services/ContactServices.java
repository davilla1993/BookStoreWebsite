package com.bookstore.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.ejb.Stateless;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Stateless
public class ContactServices extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private int result;
	private Map<String, String> errors = new HashMap<String, String>();
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	
	public ContactServices(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
	}

	
	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(String champ, String message) {
		errors.put(champ, message);
	}

	public static void sendEmail(String fromEmail, String toEmail, String username, String password, String subject,
			String message, String firstname, String lastname, String telephone)
			throws AddressException, MessagingException {

		Properties props = System.getProperties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.fallback", "false");

		Session session = Session.getDefaultInstance(props, null);
		session.setDebug(true);

		Message mailMessage = new MimeMessage(session);
		mailMessage.setFrom(new InternetAddress(fromEmail));
		mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
		mailMessage.setSubject(subject);
		mailMessage.setContent(message, "text/html");
		mailMessage.setHeader("Nom", firstname + " " + lastname);
		mailMessage.setHeader("Contact", telephone);

		Transport transport = session.getTransport("smtps");
		transport.connect("smtp.gmail.com", username, password);

		transport.sendMessage(mailMessage, mailMessage.getAllRecipients());

	}

	private void validateFirstname(String firstname) throws Exception {
		if (firstname != null) {
			if (firstname.length() < 2) {
				setErrors(firstname, "The firstname must contains minimum 3 letters");
			}
		} else {
			setErrors(firstname, "Enter your firstname");
		}
	}

	private void validateLastname(String lastname) throws Exception {
		if (lastname != null) {
			if (lastname.length() < 2) {
				setErrors(lastname, "The lastname must contains minimum 3 letters");
			}
		} else {
			setErrors(lastname, "Enter your lastname");
		}
	}

	private void validateEmail(String email) throws Exception {
		String regex = "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)";
		if (email != null && email.trim().length() != 0) {
			if (!email.matches(regex)) {
				setErrors(email, "Enter a valid email address");
			}
		} else {
			setErrors(email, "E-mail field can not be empty");
		}
	}

	private void validateTelephone(String telephone) {
		String regex = "^\\d+$";
		if (telephone != null) {
			if (!telephone.matches(regex)) {
				setErrors(telephone, "Telephone must be number");
			} else if (telephone.length() < 6) {
				setErrors(telephone, "Telephone number must contain minimum 6 digits");
			}

		} else {
			setErrors(telephone, "Enter your phone number");
		}
	}

	private void validateMessage(String message) {
		if (message != null) {
			setErrors(message, "E-mail field can not be empty");
		}
	}
	
	public void readyEmail() {
		
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String fromEmail = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String subject = request.getParameter("title");
		String message = request.getParameter("message");
		
		String username = "carlogbossou93@gmail.com";
		String password = "hfwbohkojdkbfrms";
		String toEmail = "carlogbossou93@gmail.com";
		
		try {
			validateFirstname(firstname);
			validateLastname(lastname);
			validateEmail(fromEmail);
			validateTelephone(telephone);
			validateMessage(message);
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Error - Could not validate the form");
		}
		
		if(errors.isEmpty()) {
			try {
				sendEmail(fromEmail,username,password,toEmail,subject,message,firstname,lastname,telephone);
				result = 1;
			} catch (MessagingException e) {
				e.printStackTrace();
				System.out.println("Error while sending message");
			}

		} else {
			result = -1;
		}
		
		request.setAttribute("result", result);
	}
}