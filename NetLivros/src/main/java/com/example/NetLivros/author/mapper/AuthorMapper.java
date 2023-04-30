package com.example.NetLivros.author.mapper;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.example.NetLivros.author.model.Author;
import com.example.NetLivros.author.model.dto.AuthorDTO;
import com.example.NetLivros.livro.model.Book;
import com.example.NetLivros.livro.model.dto.BookDTO;

@Component
public class AuthorMapper {

	public List<AuthorDTO> toAuthorDTOList(List<Author> authors) {
		return authors.stream().map(livro -> new AuthorDTO(livro)).toList();
	}

	public AuthorDTO toAuthorDTO(Author author) {
		AuthorDTO dto = new AuthorDTO(author);
		return dto;
	}

	public Author toAuthor(AuthorDTO authorDTO) {
		Author author = new Author();
		author.setId(authorDTO.getId());
		author.setName(authorDTO.getName());
		if (authorDTO.getBooksDTO() != null) {
			List<Book> books = authorDTO.getBooksDTO().stream().map(bookDTO -> {
				Book book = new Book();
				BeanUtils.copyProperties(bookDTO, book);
				book.setAuthor(author);
				return book;

			}).toList();
			author.setBooks(books);

		}

		return author;
	}
	
	public static List<BookDTO> verifyngAndParseToLivrosDTO(Author author) {
		if (author.getBooks() != null) {
			return author.getBooks().stream().map(book -> new BookDTO(book)).toList();
		}
		return Arrays.asList();
	}

}
