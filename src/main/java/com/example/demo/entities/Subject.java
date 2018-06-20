/**
 * 
 */
package com.example.demo.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * @author CaseyS
 *
 */

@Entity
public class Subject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@NotNull
	@Size(min = 5, max = 100)
	private String title;

	@Size(max = 1000)
	private String description;

	@OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
	private List<Book> books = new ArrayList<Book>();

	@Column(name = "created_at")
	@CreationTimestamp
	private Timestamp createdAt;

	@Transient
	private Timestamp updatedAt;

	/**
	 * 
	 */
	public Subject() {
	}

	/**
	 * @param id
	 * @param title
	 * @param description
	 * @param books
	 * @param createdAt
	 * @param updatedAt
	 */
	public Subject(int id, @NotNull @Size(min = 5, max = 100) String title, @Size(max = 1000) String description,
			List<Book> books, Timestamp createdAt, Timestamp updatedAt) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.books = books;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the books
	 */
	public List<Book> getBooks() {
		return books;
	}

	/**
	 * @param books
	 *            the books to set
	 */
	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public Book addBook(Book book) {
		getBooks().add(book);
		book.setSubject(this);

		return book;
	}

	public Book removeBook(Book book) {
		getBooks().remove(book);
		book.setSubject(null);

		return book;
	}

	public Book removeBook(int bookId) {

		Book book = getBooks().get(bookId);

		getBooks().remove(book);

		book.setSubject(null);

		return book;
	}

	/**
	 * @return the createdAt
	 */
	public Timestamp getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt
	 *            the createdAt to set
	 */
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the updatedAt
	 */
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * @param updatedAt
	 *            the updatedAt to set
	 */
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

}
