package com.example.NetLivros.author.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.example.NetLivros.book.model.Book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Author {

	@Id
	@GeneratedValue
	private UUID id;
	@Column(length = 50, nullable = false, unique = true)
	private String name;
	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
	private List<Book> books;

	
}
