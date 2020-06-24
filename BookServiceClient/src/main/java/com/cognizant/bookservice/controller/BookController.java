package com.cognizant.bookservice.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.bookservice.model.Book;
import com.cognizant.bookservice.repo.BookRepository;


@RestController
@RequestMapping("/book")
public class BookController {
	 private static final Logger logger = LoggerFactory.getLogger(BookController.class);
	@Autowired
	BookRepository bookrepo;
	
	 @GetMapping(path="/all")
	  public @ResponseBody Iterable<Book> getAllBookDetails() {
		 logger.info("inside get all book details");
	    // This returns a JSON or XML with the users
	    return bookrepo.findAll();
	    
	  }
	 @PostMapping(path="/add") // Map ONLY POST Requests
	  public @ResponseBody String addNewBook (@RequestBody Book b) {
		 logger.info("inside add new Book");
	      bookrepo.saveAndFlush(b);
	      return "Book details Saved Sucessfully!!!Thank You!!!!";
	  }
	 @PutMapping("/update/{id}")
		public Book UpdateBook(@RequestBody Book bk,@PathVariable long id)
		{
		 logger.info("inside update book details");
		   return bookrepo.findById(id).map(book -> {
	            book.setId(bk.getId());
	            book.setAuthor(bk.getAuthor());
	            book.setPrice(bk.getPrice());
	            book.setTitle(bk.getTitle());
	            return bookrepo.saveAndFlush(book);
	        }).orElseGet(() -> {
	            bk.setId(id);
	            return bookrepo.saveAndFlush(bk);
	        });
			
		}
	@DeleteMapping("/delete/{id}")
	public @ResponseBody String DeleteBook(@PathVariable long id)
	{
		 logger.info("inside delete book details");
		 bookrepo.deleteById(id);
			return "Book details Deleted Sucessfully!!!";
	}
	 

}
