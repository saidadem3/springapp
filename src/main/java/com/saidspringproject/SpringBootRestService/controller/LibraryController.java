package com.saidspringproject.SpringBootRestService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping("/addBook")
	public ResponseEntity<AddResponse> addBookImplementation(@RequestBody Library library) 
	{
		
		String id =  libraryService.buildID(library.getIsbn(),library.getAisle());
		AddResponse ad = new AddResponse();
		if (!libraryService.checkBookAlreadyExists(id)) {
			
		library.setId(id);
		repository.save(library);
		
		ad.setMsg("Book was added successfully");
		ad.setId(id);

		return new ResponseEntity<AddResponse>(ad,HttpStatus.CREATED);
		}
		
		else {
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

}
