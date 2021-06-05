package com.saidspringproject.SpringBootRestService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saidspringproject.SpringBootRestService.controller.Library;

public interface LibraryRepository extends JpaRepository<Library, String>, LibraryRepositoryCustom {

}
