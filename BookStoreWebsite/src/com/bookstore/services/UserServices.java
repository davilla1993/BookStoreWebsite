 package com.bookstore.services;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.UserDao;
import com.bookstore.entities.Users;

public class UserServices {
	
	private UserDao userDao;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public UserServices(HttpServletRequest request, HttpServletResponse response) {
		
		this.request = request;
		this.response = response;
		userDao = new UserDao();
	}
	
	public void listUser() throws ServletException, IOException{
		
		listUser(null);
	}
	
	public void listUser(String message) 
			throws ServletException, IOException {
		List<Users> listUsers = userDao.listAll();
		
		request.setAttribute("listUsers", listUsers);
		
		if(message != null) {
		request.setAttribute("message", message);
		}
		
		String listPage = "user_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		
		requestDispatcher.forward(request, response);
			
	}
	
	public void createUser() throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullname");
		String password = request.getParameter("password");
		
		Users existUser = userDao.findByEmail(email);
		
		if(existUser != null) {
			String message = "Could not create user. A user with email" + " "+ email + " " + "already exists";
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
			dispatcher.forward(request, response);
			
		}else {
		
		Users newUser = new Users(email, fullName, password);
		userDao.create(newUser);
		listUser("New user created successfully");

		}
		
	}
	
	public void editUser() throws ServletException, IOException {
		
		Integer userId = Integer.parseInt(request.getParameter("id"));
		Users user = userDao.get(userId);
		
		String editPage = "user_form.jsp";
		request.setAttribute("user", user);
		RequestDispatcher requestDispathcer = request.getRequestDispatcher(editPage);
		requestDispathcer.forward(request, response);
			
	}
	
	public void updateUser() throws ServletException, IOException {
		
		int userId = Integer.parseInt(request.getParameter("userId"));
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullname");
		String password = request.getParameter("password");
		
		Users userById = userDao.get(userId);
		Users userByEmail = userDao.findByEmail(email);
		
		if(userByEmail != null && userByEmail.getUserId() != userById.getUserId()) {
			
			String message = "Could not update user - User with this email" + " " + email + " " + "already exists.";
			request.setAttribute("message", message);
			
			RequestDispatcher requestDispathcer = request.getRequestDispatcher("message.jsp");
			requestDispathcer.forward(request, response);
		} else {
		Users user = new Users(userId, email, fullName, password);
		userDao.update(user);
		
		String message = "User has been updated successfully";
		listUser(message); 
		}
			
	}
	
	public void deleteUser() throws ServletException, IOException{
		
		int userId = Integer.parseInt(request.getParameter("id"));
		userDao.delete(userId);
		
		String message = "User has been deleted successfully";
		listUser(message);
	}
	
	public void login() throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		boolean loginResult = userDao.checkLogin(email, password);
		
		if(loginResult) {
			
			request.getSession().setAttribute("useremail", email);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/");
			dispatcher.forward(request, response);
			
		} else {
			
			String message = "Login failed";
			request.setAttribute("message", message);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
	}
		
}
