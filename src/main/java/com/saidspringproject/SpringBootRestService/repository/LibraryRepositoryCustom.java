package com.saidspringproject.SpringBootRestService.repository;

import java.util.List;

import com.saidspringproject.SpringBootRestService.controller.Library;

public interface LibraryRepositoryCustom {
	List<Library> findAllByAuthor(String authorname);

}
