package com.example.NetLivros.author.model.dto;

import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotBlank;

import com.example.NetLivros.author.mapper.AuthorMapper;
import com.example.NetLivros.author.model.Author;
import com.example.NetLivros.livro.model.dto.BookDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data 
public class AuthorDTO {

	private UUID id;
	@NotBlank
	private String name;
	private List<BookDTO> booksDTO;

	public AuthorDTO(Author author) {
		this.id = author.getId();
		this.name = author.getName();
		this.booksDTO = AuthorMapper.verifyngAndParseToLivrosDTO(author);
	}
}
