package com.books;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;

import com.books.model.Book;
import com.books.repository.BooksRepo;

@SpringBootTest
class BookStoreApplicationTests {

	@Mock
	BooksRepo repo;

	//@Test
	void contextLoads() {
		Book book = new Book();
		book.setBookID(1);
		book.setAuthors("saravanan");
		List<Book> books = new ArrayList<>();
		books.add(book);
		Mockito.when(repo.findBybookID(1)).then((Answer<?>) books);
		assertEquals(repo.findBybookID(1).get(0).getAuthors(), "saravanan");
	}

}
