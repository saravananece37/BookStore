package com.books.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.books.model.Book;

@Repository
public interface BooksRepo extends JpaRepository<Book, String> {

	
	public List<Book> findBybookID(int bookId);
	
	public List<Book> findBytitleLike(String titleContains);
}
