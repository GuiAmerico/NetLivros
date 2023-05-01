package com.example.NetLivros.livro.model.dto;

import java.util.UUID;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.NetLivros.livro.enums.Genrer;
import com.example.NetLivros.livro.model.Book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BookDTO {

	private UUID id;
	@NotBlank
	private String title;
	@NotNull
	private Integer numberOfPages;
	@Enumerated(EnumType.STRING)
	private Genrer genrer;
	private String author;

	public BookDTO(Book book) {
		this.id = book.getId();
		this.title = book.getTitle();
		this.numberOfPages = book.getNumberOfPages();
		this.genrer = book.getGenrer();
		this.author = book.getAuthor().getName();
	}

}
