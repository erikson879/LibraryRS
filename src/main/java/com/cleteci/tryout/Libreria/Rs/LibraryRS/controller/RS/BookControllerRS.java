package com.cleteci.tryout.Libreria.Rs.LibraryRS.controller.RS;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cleteci.tryout.Libreria.Rs.LibraryRS.model.Book;
import com.cleteci.tryout.Libreria.Rs.LibraryRS.repository.BookRepository;
import com.cleteci.tryout.Libreria.Rs.LibraryRS.repository.PublisherRepository;
@RestController
public class BookControllerRS {
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private PublisherRepository publisherRepository;

	
	@GetMapping(value="/api-rs/books",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Book>> getBooks(){
		Iterable<Book> book = bookRepository.findAll();
		List<Book> books = new ArrayList<>();
		book.forEach(el->books.add(el));
		if(books.isEmpty()) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(books);
		}
	}
	@GetMapping(value="/api-rs/books/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Book> getBook(@PathVariable Long id){
		Optional<Book> book = bookRepository.findById(id);
		if(book.isEmpty()) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(book.get());
		}
	}
	
	@PostMapping(value="/api-rs/books",consumes = MediaType.APPLICATION_JSON_VALUE,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Book> saveBook(@RequestBody Book entity,
			HttpServletRequest request){
		entity = saveNewPublisherIfIdNull(entity);
		entity = bookRepository.save(entity);
		URI loc;
		try {
			loc = new URI(request.getRequestURI().toString()+entity.getId());
		} catch (URISyntaxException e) {
			return ResponseEntity.unprocessableEntity().build();
		}  
		return ResponseEntity.ok().location(loc).build();
	}
	
	@PutMapping(value="/api-rs/books",consumes = MediaType.APPLICATION_JSON_VALUE,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Book> updateBook(@RequestBody Book entity,
			HttpServletRequest request){
		if(entity==null||entity.getId()==null) {
			return ResponseEntity.badRequest().build();
		}else {
			entity = saveNewPublisherIfIdNull(entity);
			entity = bookRepository.save(entity);
			URI loc =null;
			try {
				loc = new URI(request.getRequestURI().toString()+entity.getId());
			} catch (URISyntaxException e) {
				return ResponseEntity.unprocessableEntity().build();
			}
			return ResponseEntity.ok().location(loc).build();
		}
	}
		
	@DeleteMapping("/api-rs/books/{id}")
	public ResponseEntity<Book> deleteBook(@PathVariable Long id){
		bookRepository.deleteById(id);
		return ResponseEntity.accepted().build();
	}
	
	private Book saveNewPublisherIfIdNull(Book entity) {
		if(entity.getPublisher()!=null &&
				entity.getPublisher().getId()==null) {
			entity.setPublisher(publisherRepository.save(entity.getPublisher()));
		}
		return entity;
	}
}
