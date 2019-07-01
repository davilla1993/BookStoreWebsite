package com.bookstore.services;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.dao.CustomerDao;
import com.bookstore.entities.Customer;

public class CustomerServices {

	private CustomerDao customerDao;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public CustomerServices(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		this.customerDao = new CustomerDao();
	}

	public void listCustomers(String message) throws ServletException, IOException {

		List<Customer> listCustomer = customerDao.listAll();

		if (message != null) {
			request.setAttribute("message", message);
		}

		request.setAttribute("listCustomer", listCustomer);

		String listPage = "customer_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
	}

	public void listCustomers() throws ServletException, IOException {
		listCustomers(null);
	}

	private void updateCustomerFieldsFromForm(Customer customer) {

		String email = request.getParameter("email");
		String fullName = request.getParameter("fullName");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String zipcode = request.getParameter("zipCode");
		String country = request.getParameter("country");

		if(email != null && !email.equals("")) {
		customer.setEmail(email);
		}
		customer.setFullname(fullName);
		
		if(password != null && !password.equals("")) {
		customer.setPassword(password);
		}
		customer.setPhone(phone);
		customer.setAddress(address);
		customer.setCity(city);
		customer.setZipcode(zipcode);
		customer.setCountry(country);
	}

	public void createCustomer() throws ServletException, IOException {

		String email = request.getParameter("email");

		Customer existCustomer = customerDao.findByEmail(email);

		if (existCustomer != null) {
			String message = "The email " + email + " is already registered by another customer.";
			listCustomers(message);

		} else {

			Customer newCustomer = new Customer();
			updateCustomerFieldsFromForm(newCustomer);
			customerDao.create(newCustomer);

			String message = "New customer has been created successfully.";
			listCustomers(message);

		}
	}

	public void editCustomer() throws ServletException, IOException {

		Integer customerId = Integer.parseInt(request.getParameter("id"));
		Customer customer = customerDao.get(customerId);

		request.setAttribute("customer", customer);

		String editPage = "customer_form.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(editPage);
		requestDispatcher.forward(request, response);
	}

	public void updateCustomer() throws ServletException, IOException {
		Integer customerId = Integer.parseInt(request.getParameter("customerId"));
		String email = request.getParameter("email");

		Customer customerByEmail = customerDao.findByEmail(email);

		String message = null;

		if (customerByEmail != null && customerByEmail.getCustomerId() != customerId) {
			message = "Could not update the customer ID=" + customerId
					+ " because there is an existing customer having the same email";
		} else {

			Customer customerById = customerDao.get(customerId);
			updateCustomerFieldsFromForm(customerById);
			customerDao.update(customerById);

			message = "The customer has been updated succesfully";
		}

		listCustomers(message);
	}

	public void deleteCustomer() throws ServletException, IOException {
		Integer customerId = Integer.parseInt(request.getParameter("id"));
		customerDao.delete(customerId);

		String message = "The customer has been deleted successfully.";
		listCustomers(message);
	}

	public void registerCustomer() throws ServletException, IOException {

		String email = request.getParameter("email");
		Customer existCustomer = customerDao.findByEmail(email);
		String message = "";

		if (existCustomer != null) {
			message = "Couldn't register because the email " + email + " is already registered by another customer.";

		} else {

			Customer newCustomer = new Customer();
			updateCustomerFieldsFromForm(newCustomer);
			customerDao.create(newCustomer);

			message = "You have registered successfully ! Thank you.<br/> "
					+ " <a href='login'> Click here </a> to login.";

		}

		String messagePage = "frontend/message.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(messagePage);
		request.setAttribute("message", message);
		requestDispatcher.forward(request, response);
	}

	public void showLogin() throws ServletException, IOException {
		String loginPage = "frontend/login.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(loginPage);
		dispatcher.forward(request, response);

	}

	public void doLogin() throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Customer customer = customerDao.checkLogin(email, password);
		
		if (customer == null) {
			String message = "Login failed. Incorrects email or password.";
			request.setAttribute("message", message);
			showLogin();

		} else {
			
			HttpSession session = request.getSession();
			session.setAttribute("loggedCustomer", customer);
			Object objRedirectURL = session.getAttribute("redirectURL");

			if (objRedirectURL != null) {
				
				String redirectURL = (String) objRedirectURL;
				session.removeAttribute("redirectURL");
				response.sendRedirect(redirectURL);
				
			} else {

				showCustomerProfile();

			}
		}
	}
	 
	public void showCustomerProfile() throws ServletException, IOException {
		String profilePage = "frontend/customer_profile.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(profilePage);
		dispatcher.forward(request, response);
	}

	public void showCustomerProfileEditForm() throws ServletException, IOException {
		String editPage = "frontend/edit_profile.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(editPage);
		dispatcher.forward(request, response);
		
	}

	public void updateCustomerProfile() throws ServletException, IOException {
		Customer customer = (Customer) request.getSession().getAttribute("loggedCustomer");
		updateCustomerFieldsFromForm(customer);
		customerDao.update(customer);
		showCustomerProfile();
	}

}
