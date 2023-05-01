package com.example.NetLivros.author.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.NetLivros.author.model.dto.AuthorDTO;
import com.example.NetLivros.author.service.IAuthorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Api(value = "Authors API")
@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {

	private final IAuthorService service;

	@GetMapping
	@ApiOperation(value = "Get list of authors", response = AuthorDTO.class)
	public ResponseEntity<List<AuthorDTO>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Get author by id", response = AuthorDTO.class)
	public ResponseEntity<AuthorDTO> findById(@PathVariable UUID id) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
	}

	@PostMapping
	@ApiOperation(value = "Create new author", response = AuthorDTO.class, notes = "Author não pode ser nulo")
	public ResponseEntity<AuthorDTO> save(@RequestBody @Valid AuthorDTO author) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(author));
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Update existing author", response = AuthorDTO.class, notes = "Author não pode ser nulo")
	public ResponseEntity<AuthorDTO> update(@PathVariable UUID id, @RequestBody @Valid AuthorDTO author) {
		return ResponseEntity.status(HttpStatus.OK).body(service.update(id, author));
	}

	@DeleteMapping("/{id}")
	@ApiOperation("Delete existing author")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable UUID id) {
		service.deleteById(id);
	}
}
