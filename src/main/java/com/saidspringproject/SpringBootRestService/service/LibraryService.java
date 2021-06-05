package com.saidspringproject.SpringBootRestService.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saidspringproject.SpringBootRestService.controller.Library;
import com.saidspringproject.SpringBootRestService.repository.LibraryRepository;

@Service
public class LibraryService {
	@Autowired
	LibraryRepository repository;
	
	public String buildID(String Isbn, int Aisle) {
		return Isbn+Aisle;
	}
	
	public boolean checkBookAlreadyExists(String id) {
		Optional<Library> lib = repository.findById(id);
		
		if(lib.isPresent())
			return true;
		else
			return false;
	}

}
