package com.saidspringproject.SpringBootRestService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.saidspringproject.SpringBootRestService.controller.Library;
import com.saidspringproject.SpringBootRestService.repository.LibraryRepository;

@SpringBootApplication
public class SpringBootRestServiceApplication{

	@Autowired
	LibraryRepository repository;
	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestServiceApplication.class, args);
	}
	
//	@Override
//	public void run(String[] args)
//	{
//		Library lib = repository.findById("fdsefr343").get();
//		System.out.println("Java freaking sucks"
//				+"\nBut here we are, just to suffer"
//				+"\nBtw here's the author = "+ lib.getAuthor());
//		
//		Library en = new Library();
//		en.setAisle(123);
//		en.setAuthor("Nimesh");
//		en.setBook_name("Devops");
//		en.setIsbn("lkhs");
//		en.setId("lkhs123");
//		//repository.save(en);
//		
//		List<Library> allrecords = repository.findAll();
//		
//		//repository.delete(lib);
//		for(Library item: allrecords)
//		{
//			System.out.println(item.getBook_name());
//		}
//		
//	}
}


