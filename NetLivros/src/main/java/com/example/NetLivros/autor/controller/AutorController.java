package com.example.NetLivros.autor.controller;

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

import com.example.NetLivros.autor.model.dto.AutorDTO;
import com.example.NetLivros.autor.service.IAutorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Api(value = "Api de Autores")
@RestController
@RequestMapping("/api/autores")
public class AutorController {

	private final IAutorService service;

	@GetMapping
	@ApiOperation(value = "Obter lista de autores", response = AutorDTO.class)
	public ResponseEntity<List<AutorDTO>> readAll() {
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Obter autor por id", response = AutorDTO.class)
	public ResponseEntity<AutorDTO> readById(@PathVariable UUID id) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
	}

	@PostMapping
	@ApiOperation(value = "Cria novo autor", response = AutorDTO.class, notes = "Autor não pode ser nulo")
	public ResponseEntity<AutorDTO> create(@RequestBody @Valid AutorDTO autor) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(autor));
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Atualiza autor existente", response = AutorDTO.class, notes = "Autor não pode ser nulo")
	public ResponseEntity<AutorDTO> update(@PathVariable UUID id, @RequestBody @Valid AutorDTO autor) {
		return ResponseEntity.status(HttpStatus.OK).body(service.update(id, autor));
	}

	@DeleteMapping("/{id}")
	@ApiOperation("Deleta autor existente")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable UUID id) {
		service.deleteById(id);
	}
}
