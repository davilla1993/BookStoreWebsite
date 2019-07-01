package com.bookstore.entities;
// Generated 1 avr. 2019 15:39:41 by Hibernate Tools 5.2.3.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_detail", catalog = "bookstoredb")
public class OrderDetail implements java.io.Serializable {


	private static final long serialVersionUID = -3349101608333442345L;
	private OrderDetailId id = new OrderDetailId();
	private Book book;
	private BookOrder bookOrder;
	private int quantity;
	private float subtotal;

	public OrderDetail() {
	}

	public OrderDetail(OrderDetailId id) {
		this.id = id;
	}

	public OrderDetail(OrderDetailId id, Book book, BookOrder bookOrder, int quantity, float subtotal) {
		super();
		this.id = id;
		this.book = book;
		this.bookOrder = bookOrder;
		this.quantity = quantity;
		this.subtotal = subtotal;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "orderId", column = @Column(name = "order_id" , nullable = false)),
			@AttributeOverride(name = "bookId", column = @Column(name = "book_id" , nullable = false)),
		 })
	public OrderDetailId getId() {
		return this.id;
	}

	public void setId(OrderDetailId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "book_id", insertable = false, updatable = false , nullable = false)
	public Book getBook() {
		return this.book;
	}

	public void setBook(Book book) {
		this.book = book;
		this.id.setBook(book);
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", insertable = false, updatable = false , nullable = false)
	public BookOrder getBookOrder() {
		return this.bookOrder;
	}

	public void setBookOrder(BookOrder bookOrder) {
		this.bookOrder = bookOrder;
		this.id.setBookOrder(bookOrder);
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}
	
	

}
