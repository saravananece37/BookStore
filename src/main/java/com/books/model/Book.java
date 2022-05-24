package com.books.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "Books")
public class Book {

	@Id
	private int bookID;

	@Lob
	@Column(length = 10000)
	private String title;

	@Lob
	@Column(length = 10000)
	private String authors;

	@Lob
	@Column(length = 10000)
	private String average_rating;

	@Lob
	@Column(length = 10000)
	private String isbn;

	@Lob
	@Column(length = 10000)
	private String language_code;

	@Lob
	@Column(length = 10000)
	private String ratings_count;

	private float price;

	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public String getAverage_rating() {
		return average_rating;
	}

	public void setAverage_rating(String average_rating) {
		this.average_rating = average_rating;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getLanguage_code() {
		return language_code;
	}

	public void setLanguage_code(String language_code) {
		this.language_code = language_code;
	}

	public String getRatings_count() {
		return ratings_count;
	}

	public void setRatings_count(String ratings_count) {
		this.ratings_count = ratings_count;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

}
