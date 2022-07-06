package com.cleteci.tryout.Libreria.Rs.LibraryRS.controller.RS;

import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cleteci.tryout.Libreria.Rs.LibraryRS.model.Book;
import com.cleteci.tryout.Libreria.Rs.LibraryRS.model.Publisher;
import com.cleteci.tryout.Libreria.Rs.LibraryRS.repository.PublisherRepository;

@RestController
public class PublisherControllerRs {
	
	private PublisherRepository publisherRepository;

	public PublisherControllerRs(PublisherRepository publisherRepository) {
		this.publisherRepository = publisherRepository;
	}
	
	@PostMapping(value="/api-rs/publisher",consumes = MediaType.APPLICATION_JSON_VALUE,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> savePublisher(@RequestBody Publisher entity,
			HttpServletRequest request) {
		entity = publisherRepository.save(entity);
		URI loc;
		try {
			loc = new URI(request.getRequestURI().toString()+entity.getId());
		} catch (URISyntaxException e) {
			return ResponseEntity.unprocessableEntity().build();
		}  
		return ResponseEntity.ok().location(loc).build();
	}
	
	
	
}
