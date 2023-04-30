package com.example.NetLivros.livro.controller;

import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.NetLivros.livro.enums.Genrer;
import com.example.NetLivros.livro.model.dto.BookDTO;
import com.example.NetLivros.livro.service.IBookService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Api("Books API")
@RequestMapping("/api/books")
@RestController
public class BookController {

	private final IBookService service;

	@GetMapping
	@ApiOperation(value = "Get book list", response = BookDTO.class)
	public ResponseEntity<List<BookDTO>> findAll(@RequestParam(required = false) String titulo,
			@RequestParam(required = false) Integer numeroDePaginas, @RequestParam(required = false) Genrer genrer,
			@RequestParam(required = false) String editora) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll(titulo, numeroDePaginas, genrer));
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Get book by id", response = BookDTO.class)
	public ResponseEntity<BookDTO> findById(@PathVariable UUID id) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
	}

	@PostMapping("/{autorId}")
	@ApiOperation(value = "Create new book, related to an author", response = BookDTO.class, notes = "Book cannot be null")
	public ResponseEntity<BookDTO> save(@PathVariable UUID authorId, @RequestBody @Valid BookDTO livroDTO) {
		System.out.println(livroDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(authorId, livroDTO));
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Update existing book", response = BookDTO.class, notes = "Book cannot be null")
	public ResponseEntity<BookDTO> update(@PathVariable UUID id, @RequestBody @Valid BookDTO bookDTO) {
		return ResponseEntity.status(HttpStatus.OK).body(service.update(id, bookDTO));
	}

	@DeleteMapping("/{id}")
	@ApiOperation("Delete existing book")
	public void deleteById(@PathVariable UUID id) {
		service.deleteById(id);
	}

}
