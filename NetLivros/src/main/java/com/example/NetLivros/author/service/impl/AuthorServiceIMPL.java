package com.example.NetLivros.author.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.NetLivros.author.mapper.AuthorMapper;
import com.example.NetLivros.author.model.Author;
import com.example.NetLivros.author.model.dto.AuthorDTO;
import com.example.NetLivros.author.repository.AuthorRepository;
import com.example.NetLivros.author.service.IAuthorService;
import com.example.NetLivros.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class AuthorServiceIMPL implements IAuthorService {

	private final AuthorRepository authorRepository;
	private final AuthorMapper mapper;

	@Override
	public AuthorDTO save(AuthorDTO authorDTO) {
		Author author = mapper.toAuthor(authorDTO);
		authorRepository.save(author);
		authorDTO = mapper.toAuthorDTO(author);
		log.info("Saving Author in the Database");
		return authorDTO;
	}

	@Override
	public List<AuthorDTO> findAll() {
		List<Author> authores = authorRepository.findAll();
		List<AuthorDTO> authoresDTO = mapper.toAuthorDTOList(authores);

		log.info("Searching Database Authors");
		return authoresDTO;
	}

	@Override
	public AuthorDTO findById(UUID id) {
		Author author = authorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Author not found!"));
		AuthorDTO authorDTO = mapper.toAuthorDTO(author);

		log.info("Searching for Author by ID in Database");
		return authorDTO;
	}

	@Override
	public AuthorDTO update(UUID id, AuthorDTO authorDTO) {
		Author author = authorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Author not found!"));
		authorDTO.setId(id);
		author = mapper.toAuthor(authorDTO);
		authorRepository.save(author);

		log.info("Updating Author By ID in Database");
		return authorDTO;
	}

	@Override
	public void deleteById(UUID id) {
		Author author = authorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Author not found!"));

		log.info("Deleting Author By ID in Database");
		authorRepository.delete(author);
	}

	@Override
	public AuthorDTO findByName(String nome) {
		Author author = authorRepository.findByName(nome)
				.orElseThrow(() -> new ResourceNotFoundException("Author not found!"));
		AuthorDTO authorDTO = mapper.toAuthorDTO(author);

		log.info("Searching for Author by Name in the Database");
		return authorDTO;
	}
}
