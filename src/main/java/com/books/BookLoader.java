package com.books;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.books.model.Book;
import com.books.repository.DAO;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BookLoader {

	Logger logger = LoggerFactory.getLogger(BookLoader.class);
	
	@Autowired
	Executor executor;

	@Autowired
	DAO dao;
	
	@Value("${bookDetailsApiUrl}")
	private String bookApiUrl;

	public void storeBookDetails(RestTemplate restTemplate) {
		try {
			
			ResponseEntity<Object[]> response = restTemplate.getForEntity(bookApiUrl, Object[].class);
					

			Object[] ob = response.getBody();

			ObjectMapper objectMapper = new ObjectMapper();

			List<CompletableFuture<Book>> l = Arrays.stream(ob)
					.map(object -> getBookDetails(object, objectMapper)).collect(Collectors.toList());

			CompletableFuture<Void> allFutures = CompletableFuture.allOf(l.toArray(new CompletableFuture[l.size()]));

			CompletableFuture<List<Book>> allComFutures = allFutures.thenApply(v -> {
				return l.stream().map(future -> future.join()).collect(Collectors.toList());
			});

			dao.storeNewRecords(allComFutures.get());
		} catch (Exception exception) {
			logger.error("Records Update Failed :"+exception.getMessage());

		}
	}

	private CompletableFuture<Book> getBookDetails(Object object, ObjectMapper objectMapper) {
		return CompletableFuture.supplyAsync(() -> {
			return objectMapper.convertValue(object, Book.class);
		}, executor);
	}

}
