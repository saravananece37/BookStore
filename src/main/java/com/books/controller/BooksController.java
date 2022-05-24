package com.books.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.books.repository.BooksRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class BooksController{

	@Autowired
	BooksRepo repo;

	@GetMapping("/")
	private String getAllBookDetails() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(repo.findAll());
	}

	@GetMapping("/{bookID}")
	private String getBookDetailByBookID(@PathVariable(name = "bookID") int bookId) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(repo.findBybookID(bookId));
	}

	@GetMapping("/search/{title}")
	private String getBookDetailByBookTitle(@PathVariable(name = "title") String title) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(repo.findBytitleLike("%" + title + "%"));
	}
}
