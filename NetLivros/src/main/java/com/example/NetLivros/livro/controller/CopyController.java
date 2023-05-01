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

import com.example.NetLivros.livro.model.dto.CopyDTO;
import com.example.NetLivros.livro.service.ICopyService;
import com.example.NetLivros.user.listener.UserListener;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Api("Copies API")
@RequestMapping("/api/v1/books/copies")
@RestController
public class CopyController {

	private final ICopyService service;

	@ApiOperation(value = "Get list of copies", response = CopyDTO.class)
	@GetMapping
	public ResponseEntity<List<CopyDTO>> findAllCopies() {
		return ResponseEntity.status(HttpStatus.OK).body(service.findAllCopies());
	}

	@ApiOperation(value = "saving copy", response = CopyDTO.class)
	@PostMapping("/save")
	public ResponseEntity<CopyDTO> save(@RequestBody CopyDTO copyDTO) {
		return ResponseEntity.status(HttpStatus.OK).body(service.save(copyDTO));
	}

	@ApiOperation(value = "saving copies")
	@PostMapping("/save-all")
	@ResponseStatus(HttpStatus.OK)
	public void saveAllCopies(@RequestBody List<CopyDTO> copiesDTO) {
		service.saveAllCopies(copiesDTO);
	}

	@ApiOperation(value = "Get copy by title", response = CopyDTO.class)
	@GetMapping("/search/{title}")
	public ResponseEntity<CopyDTO> findCopyByTitle(@PathVariable String title) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findCopyByTitle(title));
	}

	@ApiOperation(value = "Rent copy by title", response = Object.class)
	@PostMapping("/rent/{title}")
	public ResponseEntity<Object> rentCopyByTitle(@PathVariable String title,
			@RequestBody UserListener userListener) {
		System.out.println(userListener);
		return ResponseEntity.status(HttpStatus.OK).body(service.rentCopyByTitle(title, userListener));
	}

	@ApiOperation(value = "Return  copy", response = String.class)
	@PostMapping("/return/{id}")
	public ResponseEntity<String> returnCopy(@PathVariable UUID id, @RequestBody UserListener userListener) {
		System.out.println(userListener);
		return ResponseEntity.status(HttpStatus.OK).body(service.returnCopy(id, userListener));
	}

	@ApiOperation(value = "Delete copyes by title")
	@DeleteMapping("/deleteAll/{title}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAllCopiesByTitle(@PathVariable String title) {
		service.deleteAllCopiesByTitle(title);
	}

	@ApiOperation(value = "Delete copy by id")
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAllCopiesByTitle(@PathVariable UUID id) {
		service.deleteCopyById(id);
	}
}
