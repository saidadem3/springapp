package com.saidspringproject.SpringBootRestService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.saidspringproject.SpringBootRestService.controller.Library;
import com.saidspringproject.SpringBootRestService.controller.LibraryController;
import com.saidspringproject.SpringBootRestService.repository.LibraryRepository;
import com.saidspringproject.SpringBootRestService.service.LibraryService;

@SpringBootTest
class SpringBootRestServiceApplicationTests {

	@Autowired
	LibraryController con;
	
	@MockBean
	LibraryRepository repository;
	
	@MockBean
	LibraryService libraryService;
	
	@Test
	void contextLoads() {
	}

	@Test
	public void checkBuildLogic()
	{
		LibraryService lib = new LibraryService();
		String id = lib.buildID("ZMAN", 24);
		assertEquals(id, "OLDZMAN24");
		String id1 = lib.buildID("MAN", 24);
		assertEquals(id1, "MAN24");
	}
	
	@Test
	public void addBookTest()
	{
		//mock
		Library lib = buildLibrary();
		when(libraryService.buildID(lib.getIsbn(),lib.getAisle())).thenReturn("fse");
		when(libraryService.checkBookAlreadyExists(lib.getId())).thenReturn(true);
		
		ResponseEntity response = con.addBookImplementation(buildLibrary());
		System.out.println(response.getStatusCode());
		assertEquals(response.getStatusCode(),HttpStatus.ACCEPTED);
	}
	
	public Library buildLibrary()
	{
		Library lib = new Library();
		lib.setAisle(322);
		lib.setAuthor("Rahul Shetty");
		lib.setBook_name("Spring");
		lib.setId("Sfe322");
		lib.setIsbn("sfe");
		return lib;
	}
}
