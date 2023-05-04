package com.example.NetLivros.book.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import com.example.NetLivros.author.model.Author;
import com.example.NetLivros.author.repository.AuthorRepository;
import com.example.NetLivros.book.mapper.BookMapper;
import com.example.NetLivros.book.model.Book;
import com.example.NetLivros.book.model.dto.BookDTO;
import com.example.NetLivros.book.model.enums.Genrer;
import com.example.NetLivros.book.repository.BookRepository;
import com.example.NetLivros.book.service.IBookService;
import com.example.NetLivros.exception.ResourceAlreadyExistsException;
import com.example.NetLivros.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class BookServiceIMPL implements IBookService {

	private final AuthorRepository authorRepository;
	private final BookRepository bookRepository;
	private final BookMapper mapper;

	@Override
	public BookDTO save(UUID authorId, BookDTO bookDTO) {
		if (bookRepository.existsByTitle(bookDTO.getTitle())) {
			throw new ResourceAlreadyExistsException("Book already registered!");
		}

		Author author = authorRepository.findById(authorId)
				.orElseThrow(() -> new ResourceNotFoundException("Author not found!"));
		bookDTO.setAuthor(author.getName());
		Book book = mapper.toBook(bookDTO);
		book.setAuthor(author);

		bookRepository.save(book);

		bookDTO = mapper.toBookDTO(book);
		log.info("Saving Book in the Database");

		return bookDTO;
	}

	@Override
	public List<BookDTO> findAll(String title, Integer numberOfPages, Genrer genrer) {

		Book book = new Book(title, numberOfPages, genrer);
		Example<Book> example = Example.of(book, ExampleMatcher.matching().withIgnoreCase().withIgnoreNullValues()
				.withStringMatcher(StringMatcher.CONTAINING));
		List<Book> books = bookRepository.findAll(example);
		List<BookDTO> booksDTO = mapper.toBookDTOList(books);
		log.info("Fetching Books from the Database");

		return booksDTO;
	}

	@Override
	public BookDTO findById(UUID id) {
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Book not found!"));
		BookDTO bookDTO = mapper.toBookDTO(book);
		log.info("Searching Book By ID in Database");

		return bookDTO;
	}

	@Override
	public BookDTO update(UUID id, BookDTO bookDTO) {
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Book not found!"));
		bookDTO.setAuthor(book.getAuthor().getName());
		bookDTO.setId(id);
		book = mapper.toBook(bookDTO);
		bookRepository.save(book);

		log.info("Updating Book By ID in Database");
		bookDTO = mapper.toBookDTO(book);

		return bookDTO;
	}

	@Override
	public void deleteById(UUID id) {
		bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found!"));
		log.info("Deleting Book By ID in Database");
		bookRepository.deleteById(id);
	}

}
