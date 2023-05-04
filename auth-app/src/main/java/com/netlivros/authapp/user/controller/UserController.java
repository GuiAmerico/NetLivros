package com.netlivros.authapp.user.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netlivros.authapp.dto.AuthorDTO;
import com.netlivros.authapp.dto.BookDTO;
import com.netlivros.authapp.dto.CopyDTO;
import com.netlivros.authapp.dto.UserListener;
import com.netlivros.authapp.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@RestController
public class UserController {

	private final UserService service;

	@PreAuthorize("hasRole('ROLE_AUTHOR')")
	@PostMapping("/authors/books/{id}")
	public ResponseEntity<BookDTO> save(@PathVariable UUID id, @RequestBody BookDTO bookDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(id, bookDTO));
	}

	@PreAuthorize("hasRole('ROLE_AUTHOR')")
	@PostMapping("/authors/books/copies/save")
	public ResponseEntity<CopyDTO> save(@RequestBody CopyDTO copyDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(copyDTO));
	}

	@PreAuthorize("hasRole('ROLE_AUTHOR')")
	@PostMapping("/authors/books/copies/save-all")
	public void saveAll(@RequestBody List<CopyDTO> copiesDTO) {
		service.saveAllCopies(copiesDTO);
	}

	@PreAuthorize("hasRole('ROLE_AUTHOR') or hasRole('ROLE_COMMON')")
	@PostMapping("/books/copies/rent/{title}")
	public ResponseEntity<Object> rentCopyByTitle(@PathVariable String title, @RequestBody UserListener userListener) {
		System.out.println(userListener);
		return ResponseEntity.status(HttpStatus.OK).body(service.rentCopyByTitle(title, userListener));
	};

	@PreAuthorize("hasRole('ROLE_AUTHOR') or hasRole('ROLE_COMMON')")
	@PostMapping("/books/copies/return/{id}")
	public ResponseEntity<String> returnCopy(@PathVariable UUID id, @RequestBody UserListener userListener) {
		return ResponseEntity.status(HttpStatus.OK).body(service.returnCopy(id, userListener));
	};


	@PreAuthorize("hasRole('ROLE_AUTHOR')")
	@PutMapping("/authors/update/{id}")
	public ResponseEntity<AuthorDTO> update(@PathVariable UUID id, @RequestBody AuthorDTO authorDTO) {
		return ResponseEntity.status(HttpStatus.OK).body(service.update(id, authorDTO));
	}

	@PreAuthorize("hasRole('ROLE_AUTHOR')")
	@PutMapping("/authors/books/update/{id}")
	public ResponseEntity<BookDTO> update(@PathVariable UUID id, @RequestBody BookDTO bookDTO) {
		return ResponseEntity.status(HttpStatus.OK).body(service.update(id, bookDTO));
	}

	@PreAuthorize("hasRole('ROLE_AUTHOR') or hasRole('ROLE_COMMON')")
	@DeleteMapping("/delete/{id}")
	public void deleteById(@PathVariable UUID id) {
		service.deleteById(id);
	}

	@PreAuthorize("hasRole('ROLE_AUTHOR')")
	@DeleteMapping("/authors/books/copies/delete/{id}")
	public void deleteByCopyId(@PathVariable UUID id) {
		service.deleteCopyById(id);
	}

	@PreAuthorize("hasRole('ROLE_AUTHOR')")
	@DeleteMapping("/authors/books/copies/delete/{title}")
	public void deleteById(@PathVariable String title) {
		service.deleteAllCopiesByTitle(title);
	}
}
