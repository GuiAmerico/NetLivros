package com.app.pulisher.publisher.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import com.app.pulisher.book.model.Book;

import lombok.Data;

@Data
@Entity
public class Publisher {

	@Id
	@GeneratedValue
	private UUID id;

	private String name;

	@JoinTable(name = "publisher_books", joinColumns = @JoinColumn(name = "publisher_id", referencedColumnName = "id"))
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Book> books;
}
