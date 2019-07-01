package com.bookstore.entities;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CustomerTest {

	public static void main(String[] args) {
		
          Category newCat = new Category("Core Java");		
		  EntityManagerFactory entityManagerFactory =
		  Persistence.createEntityManagerFactory("BookStoreWebsite"); EntityManager
		  entityManager = entityManagerFactory.createEntityManager();
		  
		  entityManager.getTransaction().begin(); entityManager.persist(newCat);
		  
		  entityManager.getTransaction().commit(); entityManager.close();
		  entityManagerFactory.close();
		  
		  System.out.print("A customer object was persisted");
		 
	}

}
