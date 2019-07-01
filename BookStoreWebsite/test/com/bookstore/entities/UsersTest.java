package com.bookstore.entities;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.bookstore.entities.Users;

public class UsersTest {

	public static void main(String[] args) {
		
		
		  Users user1 = new Users(); user1.setEmail("martinez213@gmail.com");
		  user1.setFullName("Sophia Martinez"); user1.setPassword("martinez08Sophia");
		  
		  EntityManagerFactory entityManagerFactory =
		  Persistence.createEntityManagerFactory("BookStoreWebsite"); EntityManager
		  entityManager = entityManagerFactory.createEntityManager();
		  
		  entityManager.getTransaction().begin(); entityManager.persist(user1);
		  
		  entityManager.getTransaction().commit(); entityManager.close();
		  entityManagerFactory.close();
		  
		  System.out.print("A Users object was persisted");
		 
	}

}
