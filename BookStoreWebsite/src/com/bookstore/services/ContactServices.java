package com.bookstore.services;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.ejb.Stateless;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Stateless
public class ContactServices {
	
	public static void sendEmail(String fromEmail, String username, String password, String toEmail, String subject, String message) 
			throws AddressException, MessagingException{
		
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
		mailMessage.setContent(message, "text/html");
		mailMessage.setSubject(subject);
		
		Transport transport = session.getTransport("smtps");
		transport.connect("smtp.gmail.com", username, password);
		
		transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
		
	}
	}