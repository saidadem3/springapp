package com.saidspringproject.SpringBootRestService.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.saidspringproject.SpringBootRestService.repository.LibraryRepository;
import com.saidspringproject.SpringBootRestService.service.LibraryService; 

@RestController
public class LibraryController {
	
	@Autowired
	LibraryRepository repository;
	
	@Autowired
	LibraryService libraryService;
	
	private static final Logger logger = LoggerFactory.getLogger(LibraryController.class);
	
	@PostMapping("/addBook")
	public ResponseEntity<AddResponse> addBookImplementation(@RequestBody Library library) 
	{
		
		String id =  libraryService.buildID(library.getIsbn(),library.getAisle());
		AddResponse ad = new AddResponse();
		if (!libraryService.checkBookAlreadyExists(id)) {
			
			logger.info("Book does not exist so creating one");
			library.setId(id);
			repository.save(library);
			
			ad.setMsg("Book was added successfully");
			ad.setId(id);
	
			return new ResponseEntity<AddResponse>(ad,HttpStatus.CREATED);
		}
		
		else {
			logger.info("Book already exists so skip creation.");
			ad.setMsg("Book already exists");
			ad.setId(id);
			return new ResponseEntity<AddResponse>(ad,HttpStatus.ACCEPTED);
		}
	}
	
	@GetMapping("/getBook/{id}")
	public Library getBookById(@PathVariable(value="id") String id)
	{
		try {
			Library lib = repository.findById(id).get();
			return lib;
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/getBooks/author")
	public List<Library> getBooksByAuthorName(@RequestParam(value="authorname") String authorname)
	{
		return repository.findAllByAuthor(authorname);
		
	}
	
	@PutMapping("/updateBook/{id}")
	public ResponseEntity<Library> updateBook(@PathVariable(value="id") String id, @RequestBody Library library) 
	{
		try {
			Library existingBook = repository.findById(id).get();
			
			existingBook.setAisle(library.getAisle());
			existingBook.setAuthor(library.getAuthor());
			existingBook.setBook_name(library.getBook_name());
			repository.save(existingBook);
			
			
			return new ResponseEntity<Library>(existingBook, HttpStatus.OK);
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
//		Library existingBook = repository.findById(id).get();
//		UpdateResponse update = new UpdateResponse();
//		if(libraryService.checkIDAlreadyExists(id)) {
//			
//			
//			existingBook.setAisle(library.getAisle());
//			existingBook.setAuthor(library.getAuthor());
//			existingBook.setBook_name(library.getBook_name());
//			repository.save(existingBook);
//			
//			update.setMsg("Book with id = %s was successfully updated!");
//			update.setAisle(library.getAisle());
//			update.setAuthor(library.getAuthor());
//			update.setBook_name(library.getBook_name());
//			update.setId(id);
//			update.setIsbn(library.getIsbn());
//			
//			
//			return new ResponseEntity<UpdateResponse>(update, HttpStatus.OK);
//		}
//		else {
//			update.setMsg("The book with that ID does not exist.");
//			update.setId(id);
//			return new ResponseEntity<UpdateResponse>(update, HttpStatus.OK);
//		}
	}
	
	@DeleteMapping("/deleteBook")
	public ResponseEntity<String> deleteBookById(@RequestBody Library library)
	{
		try {
			Library libdelete = repository.findById(library.getId()).get();
			repository.delete(libdelete);
			logger.info("Book is deleted");
			return new ResponseEntity<>("Book is deleted",HttpStatus.CREATED);
		}
		catch (Exception e) {
			logger.info("Book with this id does not exist. Cannot delete book.");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}
}
