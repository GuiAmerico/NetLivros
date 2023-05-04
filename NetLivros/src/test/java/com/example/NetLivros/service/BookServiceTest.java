package com.example.NetLivros.service;

import static com.example.NetLivros.mock.MocksAuthor.AUTHOR_1;
import static com.example.NetLivros.mock.MocksBooks.BOOKS;
import static com.example.NetLivros.mock.MocksBooks.BOOKS_DTO;
import static com.example.NetLivros.mock.MocksBooks.BOOK_1;
import static com.example.NetLivros.mock.MocksBooks.BOOK_DTO_1;
import static com.example.NetLivros.mock.MocksBooks.BOOK_DTO_2;
import static com.example.NetLivros.mock.MocksBooks.BOOK_DTO_3;
import static com.example.NetLivros.mock.MocksBooks.INVALID_BOOK;
import static com.example.NetLivros.mock.MocksBooks.INVALID_BOOK_DTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.NetLivros.author.repository.AuthorRepository;
import com.example.NetLivros.book.mapper.BookMapper;
import com.example.NetLivros.book.model.Book;
import com.example.NetLivros.book.model.dto.BookDTO;
import com.example.NetLivros.book.model.enums.Genrer;
import com.example.NetLivros.book.repository.BookRepository;
import com.example.NetLivros.book.service.impl.BookServiceIMPL;
import com.example.NetLivros.exception.ResourceNotFoundException;
import com.example.NetLivros.exception.ResourceNotValidException;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

	@InjectMocks
	private BookServiceIMPL service;
	@Mock
	private BookRepository repository;
	@Mock
	private AuthorRepository authorRepository;
	@Mock
	private BookMapper mapper;

	@DisplayName("Should save book without throwing exception")
	@Test
	void testSave() {
		when(authorRepository.findById(any(UUID.class))).thenReturn(Optional.of(AUTHOR_1));
		when(repository.save(any(Book.class))).thenReturn(BOOK_1);
		when(mapper.toBook(any())).thenReturn(BOOK_1);
		when(mapper.toBookDTO(any())).thenReturn(BOOK_DTO_1);
		
		
		BookDTO savedBook = service.save(UUID.randomUUID(), BOOK_DTO_1);
		
		assertThat(savedBook).isEqualTo(BOOK_DTO_1);
		
	}

	@DisplayName("Should throw exception when trying to save invalid book")
	@Test
	void testSave_InvalidBook() {
		when(authorRepository.findById(any(UUID.class))).thenReturn(Optional.of(AUTHOR_1));
		when(repository.save(INVALID_BOOK)).thenThrow(ResourceNotValidException.class);
		lenient().when(mapper.toBook(any())).thenReturn(INVALID_BOOK);
		lenient().when(mapper.toBookDTO(any())).thenReturn(INVALID_BOOK_DTO);

		assertThatThrownBy(() -> service.save(UUID.randomUUID(), INVALID_BOOK_DTO)).isInstanceOf(ResourceNotValidException.class);

	}

	@DisplayName("Should list books without throwing exception")
	@Test
	void testFindAll() {
		lenient().when(repository.findAll()).thenReturn(BOOKS);
		when(mapper.toBookDTOList(any())).thenReturn(BOOKS_DTO);

		List<BookDTO> livrosList = service.findAll(null, null, null);

		assertThat(livrosList).isEqualTo(BOOKS_DTO);
		assertThat(livrosList).hasSize(3);
		assertThat(livrosList.get(0)).isEqualTo(BOOK_DTO_1);
		assertThat(livrosList.get(1)).isEqualTo(BOOK_DTO_2);
		assertThat(livrosList.get(2)).isEqualTo(BOOK_DTO_3);

	}

	@DisplayName("Should fetch for a book without throwing an exception")
	@Test
	void testFindById() {
		when(repository.findById(any(UUID.class))).thenReturn(Optional.of(BOOK_1));
		when(mapper.toBookDTO(any())).thenReturn(BOOK_DTO_1);
		
		BookDTO livroDTO = service.findById(UUID.randomUUID());
		
		assertThat(livroDTO).isEqualTo(BOOK_DTO_1);
		
	}
	@DisplayName("Should throw exception when fetching a non-existent book")
	@Test
	void testFindById_BookNotFound() {
		when(repository.findById(any(UUID.class))).thenThrow(ResourceNotFoundException.class);
		
		assertThatThrownBy(() -> service.findById(UUID.randomUUID())).isInstanceOf(ResourceNotFoundException.class);
		
	}

	@DisplayName("Should update author without throwing exception")
	@Test
	void testUpdate() {
		when(repository.findById(any(UUID.class))).thenReturn(Optional.of(BOOK_1));
		when(mapper.toBookDTO(any())).thenReturn(BOOK_DTO_1);
		when(mapper.toBook(any())).thenReturn(BOOK_1);
		
		BOOK_DTO_1.setTitle("Title Updated");
		BOOK_DTO_1.setGenrer(Genrer.COMEDY);
		BOOK_DTO_1.setNumberOfPages(123);
		BookDTO updatedBook = service.update(UUID.randomUUID(), BOOK_DTO_1);
		
		assertThat(updatedBook.getTitle()).isEqualTo(BOOK_DTO_1.getTitle());
		assertThat(updatedBook.getGenrer()).isEqualTo(BOOK_DTO_1.getGenrer());
		assertThat(updatedBook.getNumberOfPages()).isEqualTo(BOOK_DTO_1.getNumberOfPages());
		
		
	}

	@DisplayName("Should delete book without throwing exception")
	@Test
	void testDeleteById() {
		when(repository.findById(any(UUID.class))).thenReturn(Optional.of(BOOK_1));

		assertDoesNotThrow(() -> service.deleteById(UUID.randomUUID()));

	}
	
	@DisplayName("Should throw exception trying to delete non-existent book")
	@Test
	void testDeleteById_BookNotFound() {
		when(repository.findById(any(UUID.class))).thenThrow(ResourceNotFoundException.class);
		
		assertThatThrownBy(() -> service.deleteById(UUID.randomUUID())).isInstanceOf(ResourceNotFoundException.class);
		
	}
	
	
	

}
