package com.example.NetLivros.service;

import static com.example.NetLivros.mock.MocksAuthor.AUTHORES;
import static com.example.NetLivros.mock.MocksAuthor.AUTHORES_DTO;
import static com.example.NetLivros.mock.MocksAuthor.AUTHOR_1;
import static com.example.NetLivros.mock.MocksAuthor.AUTHOR_DTO_1;
import static com.example.NetLivros.mock.MocksAuthor.AUTHOR_DTO_2;
import static com.example.NetLivros.mock.MocksAuthor.AUTHOR_DTO_3;
import static com.example.NetLivros.mock.MocksAuthor.INVALID_AUTHOR;
import static com.example.NetLivros.mock.MocksAuthor.INVALID_AUTHOR_DTO;
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

import com.example.NetLivros.author.mapper.AuthorMapper;
import com.example.NetLivros.author.model.Author;
import com.example.NetLivros.author.model.dto.AuthorDTO;
import com.example.NetLivros.author.repository.AuthorRepository;
import com.example.NetLivros.author.service.impl.AuthorServiceIMPL;
import com.example.NetLivros.exception.ResourceNotFoundException;
import com.example.NetLivros.exception.ResourceNotValidException;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

	@InjectMocks
	private AuthorServiceIMPL service;
	@Mock
	private AuthorRepository repository;
	@Mock
	private AuthorMapper mapper;

	@Test
	@DisplayName("Should save author without throwing exception")
	void testSave() {
		when(repository.save(any(Author.class))).thenReturn(AUTHOR_1);
		when(mapper.toAuthor(any())).thenReturn(AUTHOR_1);
		when(mapper.toAuthorDTO(any())).thenReturn(AUTHOR_DTO_1);
		
		AuthorDTO savedAuthor = service.save(AUTHOR_DTO_1);

		assertThat(savedAuthor).isEqualTo(AUTHOR_DTO_1);
		
	}

	@Test
	@DisplayName("Should throw exception when saving author with invalid arguments")
	void testSave_InvalidAuthor() {

		when(repository.save(INVALID_AUTHOR)).thenThrow(ResourceNotValidException.class);
		lenient().when(mapper.toAuthor(any())).thenReturn(INVALID_AUTHOR);
		lenient().when(mapper.toAuthorDTO(any())).thenReturn(INVALID_AUTHOR_DTO);

		assertThatThrownBy(() -> service.save(INVALID_AUTHOR_DTO)).isInstanceOf(ResourceNotValidException.class);

	}

	@DisplayName("Should list authors without throwing exception")
	@Test
	void testFindAll() {
		when(repository.findAll()).thenReturn(AUTHORES);
		when(mapper.toAuthorDTOList(any())).thenReturn(AUTHORES_DTO);
		
		List<AuthorDTO> listAuthores = service.findAll();
		
		assertThat(listAuthores).isNotNull();
		assertThat(listAuthores).hasSize(3);
		assertThat(listAuthores.get(0)).isEqualTo(AUTHOR_DTO_1);
		assertThat(listAuthores.get(1)).isEqualTo(AUTHOR_DTO_2);
		assertThat(listAuthores.get(2)).isEqualTo(AUTHOR_DTO_3);
	}

	@DisplayName("Should search for an author without throwing an exception")
	@Test
	void testFindById() {
		when(repository.findById(any(UUID.class))).thenReturn(Optional.of(AUTHOR_1));
		when(mapper.toAuthorDTO(any())).thenReturn(AUTHOR_DTO_1);
		
		AuthorDTO autorDTO = service.findById(UUID.randomUUID());
		
		assertThat(autorDTO).isEqualTo(AUTHOR_DTO_1);

		
		
	}

	@DisplayName("Should throw exception when searching for a non-existent author")
	@Test
	void testFindById_AuthorNotFound() {
		when(repository.findById(any(UUID.class))).thenThrow(ResourceNotFoundException.class);

		assertThatThrownBy(() -> service.findById(UUID.randomUUID())).isInstanceOf(ResourceNotFoundException.class);
	}

	@DisplayName("Should save author without throwing exception")
	@Test
	void testUpdate() {
		when(repository.findById(any(UUID.class))).thenReturn(Optional.of(AUTHOR_1));
		when(repository.save(any(Author.class))).thenReturn(AUTHOR_1);
		when(mapper.toAuthor(any())).thenReturn(AUTHOR_1);
		
		AUTHOR_DTO_1.setName("Updated Author");
		AuthorDTO updatedAuthor = service.update(UUID.randomUUID(),AUTHOR_DTO_1);

		assertThat(updatedAuthor).isEqualTo(AUTHOR_DTO_1);
		assertThat(updatedAuthor.getName()).isEqualTo("Updated Author");
	}

	@DisplayName("Should delete author without throwing exception")
	@Test
	void testDeleteById() {
		when(repository.findById(any(UUID.class))).thenReturn(Optional.of(AUTHOR_1));
		
		assertDoesNotThrow(() -> service.deleteById(UUID.randomUUID()));

	}

	@DisplayName("Should throw exception when trying to delete non-existent author")
	@Test
	void testDeleteById_AuthorNotFound() {
		when(repository.findById(any(UUID.class))).thenThrow(ResourceNotFoundException.class);
		
		assertThatThrownBy(() -> service.deleteById(UUID.randomUUID())).isInstanceOf(ResourceNotFoundException.class);
		
	}
}
