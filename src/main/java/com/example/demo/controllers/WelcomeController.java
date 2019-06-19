package com.example.demo.controllers;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entities.Book;
import com.example.demo.entities.Subject;
import com.example.demo.service.BookService;
import com.example.demo.service.SubjectService;

@Controller
//@CrossOrigin(origins = "http://localhost:8080")
public class WelcomeController {

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private BookService bookService;

	// inject via application.properties
	@Value("${welcome.message:test}")
	private String message = "Hello World";

	@RequestMapping("")
	public String welcome(Map<String, Object> model) {

		model.put("message", this.message);

		model.put("allSubjects", subjectService.getAllSubjects());

		return "welcome";
	}

	@GetMapping("create-subject")
	public ModelAndView createSubjectView(@ModelAttribute("subject") Subject subject, ModelMap model) {

		subject = new Subject();

		model.addAttribute("books", subject.getBooks());

		return new ModelAndView("subjectForm", "subject", subject);
	}

	@PostMapping(value = "create-subject")
	public ModelAndView createSubject(HttpServletRequest req, @Valid @ModelAttribute("subject") Subject subject,
			BindingResult result, ModelMap model) {

		// Check if rowId parameter exists
		if (req.getParameter("removeBook") != null) {

			int rowId = Integer.parseInt(req.getParameter("removeBook"));

			removeRow(rowId, subject);

		} else {

			if (result.hasErrors()) {

				model.addAttribute("books", subject.getBooks());

				return new ModelAndView("/subjectForm", "subject", subject);

			}
			if (subject.getId() > 0) {

				subjectService.updateSubject(subject);

			} else {

			}
			subjectService.addSubject(subject);
			return new ModelAndView("redirect:", "allSubjects", subjectService.getAllSubjects());
		}

		return new ModelAndView("subjectForm", "subject", subject);
	}

	/*
	 * 
	 * 
	 */
	@GetMapping("update-subject/{id}")
	public ModelAndView updateSubjectView(@PathVariable int id, Subject subject, ModelMap model) {

		return new ModelAndView("subjectForm", "subject", subjectService.findSubjectById(id));
	}
	/*
	 * 
	 * 
	 */

	@GetMapping("subject-info/{id}")
	public ModelAndView subjectDetailsView(@PathVariable int id) {

		return new ModelAndView("subjectDetails", "subject", subjectService.findSubjectById(id));
	}

	/*
	 * 
	 * 
	 */

	@GetMapping("delete-subject/{id}")
	public ModelAndView deleteSubject(@PathVariable int id, ModelMap model) {

		subjectService.deleteSubject(id);

		return new ModelAndView("", "allSubjects", subjectService.getAllSubjects());
	}

	/*
	 * 
	 * 
	 */

	@GetMapping("delete-book/{id}")
	public ModelAndView deleteBook(@PathVariable int id) {

		Book book = bookService.findBookById(id);

		bookService.deleteBook(id);

		return new ModelAndView("redirect:/subject-info/" + book.getSubject().getId(), "subject", book.getSubject());
	}

	/*
	 * 
	 * 
	 */
	@PostMapping(value = "create-subject", params = { "addBook" })
	public ModelAndView addRow(@ModelAttribute("subject") Subject subject, ModelMap model) {

		subject.addBook(new Book());

		model.addAttribute("books", subject.getBooks());

		return new ModelAndView("subjectForm", "subject", subject);
	}

	public void removeRow(int rowId, Subject subject) {

		try {

			subject.removeBook(rowId);

		} catch (IndexOutOfBoundsException ie) {

		}

	}

}
