package com.example.NetLivros.service;

import static com.example.NetLivros.mock.MocksAutor.AUTORES;
import static com.example.NetLivros.mock.MocksAutor.AUTORES_DTO;
import static com.example.NetLivros.mock.MocksAutor.AUTOR_1;
import static com.example.NetLivros.mock.MocksAutor.AUTOR_DTO_1;
import static com.example.NetLivros.mock.MocksAutor.AUTOR_DTO_2;
import static com.example.NetLivros.mock.MocksAutor.AUTOR_DTO_3;
import static com.example.NetLivros.mock.MocksAutor.INVALID_AUTOR;
import static com.example.NetLivros.mock.MocksAutor.INVALID_AUTOR_DTO;
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

import com.example.NetLivros.autor.mapper.AutorMapper;
import com.example.NetLivros.autor.model.Autor;
import com.example.NetLivros.autor.model.dto.AutorDTO;
import com.example.NetLivros.autor.repository.AutorRepository;
import com.example.NetLivros.autor.service.impl.AutorServiceIMPL;
import com.example.NetLivros.exception.ResourceNotFoundException;
import com.example.NetLivros.exception.ResourceNotValidException;

@ExtendWith(MockitoExtension.class)
class AutorServiceTest {

	@InjectMocks
	private AutorServiceIMPL service;
	@Mock
	private AutorRepository repository;
	@Mock
	private AutorMapper mapper;

	@Test
	@DisplayName("Deveria salvar autor sem lançar exceção")
	void testSave() {
		when(repository.save(any(Autor.class))).thenReturn(AUTOR_1);
		when(mapper.toAutor(any())).thenReturn(AUTOR_1);
		when(mapper.toAutorDTO(any())).thenReturn(AUTOR_DTO_1);
		
		AutorDTO savedAutor = service.save(AUTOR_DTO_1);

		assertThat(savedAutor).isEqualTo(AUTOR_DTO_1);
		
	}

	@Test
	@DisplayName("Deveria lançar exceção ao salvar autor com argumentos invalidos")
	void testSave_InvalidAutor() {

		when(repository.save(INVALID_AUTOR)).thenThrow(ResourceNotValidException.class);
		lenient().when(mapper.toAutor(any())).thenReturn(INVALID_AUTOR);
		lenient().when(mapper.toAutorDTO(any())).thenReturn(INVALID_AUTOR_DTO);

		assertThatThrownBy(() -> service.save(INVALID_AUTOR_DTO)).isInstanceOf(ResourceNotValidException.class);

	}

	@DisplayName("Deveria listar autores sem lançar exceção")
	@Test
	void testFindAll() {
		when(repository.findAll()).thenReturn(AUTORES);
		when(mapper.toAutorDTOList(any())).thenReturn(AUTORES_DTO);
		
		List<AutorDTO> listAutores = service.findAll();
		
		assertThat(listAutores).isNotNull();
		assertThat(listAutores).hasSize(3);
		assertThat(listAutores.get(0)).isEqualTo(AUTOR_DTO_1);
		assertThat(listAutores.get(1)).isEqualTo(AUTOR_DTO_2);
		assertThat(listAutores.get(2)).isEqualTo(AUTOR_DTO_3);
	}

	@DisplayName("Deveria buscar um autor sem lançar exceção")
	@Test
	void testFindById() {
		when(repository.findById(any(UUID.class))).thenReturn(Optional.of(AUTOR_1));
		when(mapper.toAutorDTO(any())).thenReturn(AUTOR_DTO_1);
		
		AutorDTO autorDTO = service.findById(UUID.randomUUID());
		
		assertThat(autorDTO).isEqualTo(AUTOR_DTO_1);

		
		
	}

	@DisplayName("Deveria lançar exceção ao buscar um autor inexistente ")
	@Test
	void testFindById_AutorNotFound() {
		when(repository.findById(any(UUID.class))).thenThrow(ResourceNotFoundException.class);

		assertThatThrownBy(() -> service.findById(UUID.randomUUID())).isInstanceOf(ResourceNotFoundException.class);
	}

	@DisplayName("Deveria salvar autor sem lançar exceção")
	@Test
	void testUpdate() {
		when(repository.findById(any(UUID.class))).thenReturn(Optional.of(AUTOR_1));
		when(repository.save(any(Autor.class))).thenReturn(AUTOR_1);
		when(mapper.toAutor(any())).thenReturn(AUTOR_1);
		
		AUTOR_DTO_1.setNome("Updated Autor");
		AutorDTO updatedAutor = service.update(UUID.randomUUID(),AUTOR_DTO_1);

		assertThat(updatedAutor).isEqualTo(AUTOR_DTO_1);
		assertThat(updatedAutor.getNome()).isEqualTo("Updated Autor");
	}

	@DisplayName("Deveria deletar autor sem lançar exceção")
	@Test
	void testDeleteById() {
		when(repository.findById(any(UUID.class))).thenReturn(Optional.of(AUTOR_1));
		
		assertDoesNotThrow(() -> service.deleteById(UUID.randomUUID()));

	}

	@DisplayName("Deveria lançar exceção ao tentar deletar autor inexistente")
	@Test
	void testDeleteById_AutorNotFound() {
		when(repository.findById(any(UUID.class))).thenThrow(ResourceNotFoundException.class);
		
		assertThatThrownBy(() -> service.deleteById(UUID.randomUUID())).isInstanceOf(ResourceNotFoundException.class);
		
	}
}
