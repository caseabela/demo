/**
 * 
 */
package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Book;
import com.example.demo.entities.Subject;
import com.example.demo.repository.SubjectRepository;

/**
 * @author CaseyS
 *
 */
@Service
public class SubjectService {

	@Autowired
	private SubjectRepository subjectRepository;

	public void addSubject(@Valid Subject subject) {

		List<Book> books = new ArrayList<Book>();

		for (Book book : subject.getBooks()) {

			book.setSubject(subject);

			books.add(book);

		}

		subject.setBooks(books);

		subjectRepository.save(subject);

	}

	public void updateSubject(@Valid Subject subject) {

		List<Book> books = new ArrayList<Book>();

		for (Book book : subject.getBooks()) {

			book.setSubject(subject);

			books.add(book);

		}

		subject.setBooks(books);

		subjectRepository.save(subject);
	}

	public void deleteSubject(int id) {
		subjectRepository.deleteById(id);
	}

	public Subject findSubjectById(int id) {
		return subjectRepository.getOne(id);

	}

	public List<Subject> getAllSubjects() {
		return subjectRepository.findAll();
	}

}
