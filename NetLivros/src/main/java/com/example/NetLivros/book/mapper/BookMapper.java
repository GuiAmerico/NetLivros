package com.example.NetLivros.book.mapper;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.example.NetLivros.author.model.Author;
import com.example.NetLivros.author.model.dto.AuthorDTO;
import com.example.NetLivros.author.service.IAuthorService;
import com.example.NetLivros.book.model.Book;
import com.example.NetLivros.book.model.dto.BookDTO;

@Component
public class BookMapper {

	private final IAuthorService service;

	public BookMapper(IAuthorService service) {
		this.service = service;
	}

	public List<BookDTO> toBookDTOList(List<Book> books) {
		return books.stream().map(l -> new BookDTO(l)).toList();
	}

	public BookDTO toBookDTO(Book book) {
		BookDTO dto = new BookDTO(book);
		return dto;
	}

	public Book toBook(BookDTO bookDTO) {
		Book book = new Book();
		book.setId(bookDTO.getId());
		book.setTitle(bookDTO.getTitle());
		book.setNumberOfPages(bookDTO.getNumberOfPages());
		book.setGenrer(bookDTO.getGenrer());
		AuthorDTO authorDTO = service.findByName(bookDTO.getAuthor());
		Author author = new Author();
		BeanUtils.copyProperties(authorDTO, author);

		List<Book> books = author.getBooks();

		author.setBooks(books);

		book.setAuthor(author);

		return book;
	}

}
