/**
 * 
 */
package com.example.demo.service;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Book;
import com.example.demo.repository.BookRepository;

/**
 * @author CaseyS
 *
 */
@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	public void addBook(@Valid Book book) {
		bookRepository.save(book);

	}

	public void updateSUbject(@Valid Book book) {
		bookRepository.save(book);
	}

	public void deleteBook(int id) {
		bookRepository.deleteById(id);
	}

	public Book findBookById(int id) {
		return bookRepository.getOne(id);

	}

	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

}
