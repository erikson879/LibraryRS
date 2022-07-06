package com.cleteci.tryout.Libreria.Rs.LibraryRS.controller.RS;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cleteci.tryout.Libreria.Rs.LibraryRS.model.Author;
import com.cleteci.tryout.Libreria.Rs.LibraryRS.model.Book;
import com.cleteci.tryout.Libreria.Rs.LibraryRS.repository.AuthorRepository;

@RestController
public class AuthorControllerRS {
	@Autowired
	AuthorRepository authorRepository;

	@GetMapping(value="/api-rs/authors",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Author>> getAuthors(){
		Iterable<Author> author = authorRepository.findAll();
		List<Author> authors = new ArrayList<>();
		author.forEach(el->authors.add(el));
		if(authors.isEmpty()) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(authors);
		}
	}
}
