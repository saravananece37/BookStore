package com.books.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.books.BookStoreApplication;
import com.books.model.Book;

@Service
@EnableTransactionManagement
public class DAO {

	Logger logger = LoggerFactory.getLogger(DAO.class);
	
	@Autowired
	BooksRepo repo;

	public void deleteOldRecords() {
		repo.deleteAll();
	}

	@Transactional(rollbackOn = Exception.class)
	public void storeNewRecords(List<Book> books) {
		logger.info("Number of records :"+books.size());
		repo.deleteAll();
		repo.saveAll(books);
	}

}
