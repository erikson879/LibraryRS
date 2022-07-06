package com.cleteci.tryout.Libreria.Rs.LibraryRS.data;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.cleteci.tryout.Libreria.Rs.LibraryRS.model.Author;
import com.cleteci.tryout.Libreria.Rs.LibraryRS.model.Book;
import com.cleteci.tryout.Libreria.Rs.LibraryRS.model.Publisher;
import com.cleteci.tryout.Libreria.Rs.LibraryRS.repository.AuthorRepository;
import com.cleteci.tryout.Libreria.Rs.LibraryRS.repository.BookRepository;
import com.cleteci.tryout.Libreria.Rs.LibraryRS.repository.PublisherRepository;

@Component
public class BootstrapData implements ApplicationListener<ContextRefreshedEvent>{

	private AuthorRepository authorRepository;
	private BookRepository bookReporsitory;
	private PublisherRepository publisherRepository;
	
	
	public BootstrapData(AuthorRepository authorRepository, BookRepository bookReporsitory,
			PublisherRepository publisherRepository) {
		this.authorRepository = authorRepository;
		this.bookReporsitory = bookReporsitory;
		this.publisherRepository = publisherRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		initData();		
	}

	private void initData() {
		Publisher e1 = new Publisher("Packt");
		Publisher e2 = new Publisher("O'REILLY");
		publisherRepository.save(e1);
		publisherRepository.save(e2);
		
		Author a1 = new Author("Erikson", "Rodriguez");
		com.cleteci.tryout.Libreria.Rs.LibraryRS.model.Book b1 = new Book("1234", "java",  500L, e1);
		a1.getBooks().add(b1);
		b1.getAuthors().add(a1);
		
		authorRepository.save(a1);
		bookReporsitory.save(b1);
		Author a2 = new Author("Danni", "Do Santos");
		Book b2 = new Book("1235", "Fundamentos POO",  300L, e2);
		a2.getBooks().add(b2);
		b2.getAuthors().add(a2);		
		authorRepository.save(a2);
		bookReporsitory.save(b2);
	}

}
