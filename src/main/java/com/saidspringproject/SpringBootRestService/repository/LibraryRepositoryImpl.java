package com.saidspringproject.SpringBootRestService.repository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.saidspringproject.SpringBootRestService.controller.Library;



public class LibraryRepositoryImpl implements LibraryRepositoryCustom {
	
	@Autowired
	LibraryRepository repository;

	@Override
	public List<Library> findAllByAuthor(String authorname) {
		List<Library> booksWithAuthor = new ArrayList<Library>();
		List<Library> books = repository.findAll();
		
		for(Library item: books) {
			if (item.getAuthor().equalsIgnoreCase(authorname)) {
				booksWithAuthor.add(item);
			}
		}
		return booksWithAuthor;
	}
	


}
