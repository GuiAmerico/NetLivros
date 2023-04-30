package com.example.NetLivros.livro.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.example.NetLivros.author.model.Author;
import com.example.NetLivros.livro.enums.Genrer;
import com.example.NetLivros.livro.model.enums.BookSituation;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class Book {

	@Id
	@GeneratedValue
	private UUID id;
	@Column(length = 50, nullable = false, unique = true)
	private String title;
	@Column(nullable = false)
	private Integer numberOfPages;
	@Column(length = 50, nullable = false)
	@Enumerated(EnumType.STRING)
	private Genrer genrer;
	@Column
	@Enumerated(EnumType.STRING)
	private BookSituation situation;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "book_copies", joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"))
	private List<Copy> copies;
	@ManyToOne
	private Author author;

	public Book(String title, Integer numberOfPages, Genrer genrer) {
		this.title = title;
		this.numberOfPages = numberOfPages;
		this.genrer = genrer;
	}

	public void addCopy(Copy copy) {
		this.copies.add(copy);
	}

}
