package com.cleteci.tryout.Libreria.Rs.LibraryRS.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String ISBN;
	private String title;
	//@JsonManagedReference
	@ManyToMany
	@JoinTable(name = "author_book",joinColumns = @JoinColumn(name = "book_id"), 
	inverseJoinColumns = @JoinColumn(name="author_id"))
	private Set<Author> authors=new HashSet<>();;
	private Long numPgs;
	@ManyToOne
	private Publisher publisher;
	public Book() {}
	public Book(String iSBN, String title, Set<Author> authors, Long numPgs, Publisher publisher) {
		ISBN = iSBN;
		this.title = title;
		this.authors = authors;
		this.numPgs = numPgs;
		this.publisher = publisher;
	}
	public Book(String iSBN, String title,  Long numPgs, Publisher publisher) {
		ISBN = iSBN;
		this.title = title;
		this.numPgs = numPgs;
		this.publisher = publisher;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Set<Author> getAuthors() {
		return authors;
	}
	public void setAutores(Set<Author> authors) {
		this.authors = authors;
	}
	public Long getNumPgs() {
		return numPgs;
	}
	public void setNumPgs(Long numPgs) {
		this.numPgs = numPgs;
	}
	public Publisher getPublisher() {
		return publisher;
	}
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
