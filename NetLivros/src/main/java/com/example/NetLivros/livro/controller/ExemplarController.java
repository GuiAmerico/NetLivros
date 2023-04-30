package com.example.NetLivros.livro.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.NetLivros.livro.model.dto.ExemplarDTO;
import com.example.NetLivros.livro.model.dto.LivroDTO;
import com.example.NetLivros.livro.service.IExemplarService;
import com.example.NetLivros.usuario.listener.UsuarioListener;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Api("Api de exemplares") 
@RequestMapping("/api/v1/livros/exemplares")
@RestController
public class ExemplarController {

	private final IExemplarService service;

	@ApiOperation(value = "Obter lista de exemplares", response = LivroDTO.class)
	@GetMapping
	public ResponseEntity<List<ExemplarDTO>> findAllExemplares() {
		return ResponseEntity.status(HttpStatus.OK).body(service.findAllExemplares());
	}

	@ApiOperation(value = "Salvando exemplar", response = ExemplarDTO.class)
	@PostMapping("/save")
	public ResponseEntity<ExemplarDTO> save(@RequestBody ExemplarDTO exemplarDTO) {
		return ResponseEntity.status(HttpStatus.OK).body(service.save(exemplarDTO));
	}

	@ApiOperation(value = "Salvando exemplares")
	@PostMapping("/save-all")
	@ResponseStatus(HttpStatus.OK)
	public void saveAllExemplares(@RequestBody List<ExemplarDTO> exemplaresDTO) {
		service.saveAllExemplares(exemplaresDTO);
	}

	@ApiOperation(value = "Obter exemplar por titulo", response = ExemplarDTO.class)
	@GetMapping("buscar/{titulo}")
	public ResponseEntity<ExemplarDTO> findExemplarByTitulo(@PathVariable String titulo) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findExemplarByTitulo(titulo));
	}

	
	@ApiOperation(value = "Alugar exemplar por titulo", response = Object.class)
	@PostMapping("/alugar/{titulo}")
	public ResponseEntity<Object> alugarExemplarByTitulo(@PathVariable String titulo,
			@RequestBody UsuarioListener usuarioListener) {
		System.out.println(usuarioListener);
		return ResponseEntity.status(HttpStatus.OK).body(service.alugarExemplarByTitulo(titulo, usuarioListener));
	}

	@ApiOperation(value = "Devolver exemplar", response = String.class)
	@PostMapping("/devolver/{id}")
	public ResponseEntity<String> devolverExemplar(@PathVariable UUID id,
			UsuarioListener usuarioListener) {
		System.out.println(id);
		return ResponseEntity.status(HttpStatus.OK).body(service.devolverExemplar(id, usuarioListener));
	}
	

	@ApiOperation(value = "Deletar exemplares por titulo")
	@DeleteMapping("/deleteAll/{titulo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAllExemplaresByTitulo(@PathVariable String titulo) {
		service.deleteAllExemplaresByTitulo(titulo);
	}	

	@ApiOperation(value = "Deletar exemplar por ID")
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAllExemplaresByTitulo(@PathVariable UUID id) {
		service.deleteExemplarById(id);
	}
}
