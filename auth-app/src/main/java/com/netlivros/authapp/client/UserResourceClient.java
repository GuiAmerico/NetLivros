package com.netlivros.authapp.client;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.netlivros.authapp.dto.AuthorDTO;
import com.netlivros.authapp.dto.BookDTO;
import com.netlivros.authapp.dto.CopyDTO;
import com.netlivros.authapp.dto.UserListener;

@FeignClient(value = "msnetlivros", path = "/api/v1/")
public interface UserResourceClient {

	@PostMapping("/authors")
	public ResponseEntity<AuthorDTO> save(@RequestBody @Valid AuthorDTO author);

	@PutMapping("/authors/{id}")
	public ResponseEntity<AuthorDTO> update(@PathVariable UUID id, @RequestBody @Valid AuthorDTO author);

	@DeleteMapping("/authors/{id}")
	public void deleteById(@PathVariable UUID id);

	@PostMapping("/books/copies/rent/{title}")
	public ResponseEntity<Object> rentCopyByTitle(@PathVariable String title, @RequestBody UserListener userListener);

	@PostMapping("/books/copies/return/{id}")
	public ResponseEntity<String> returnCopy(@PathVariable UUID id, @RequestBody UserListener userListener);

	@PutMapping("/books/{id}")
	public ResponseEntity<BookDTO> update(@PathVariable UUID id, @RequestBody @Valid BookDTO bookDTO);

	@DeleteMapping("/books/{id}")
	public void deleteBookById(@PathVariable UUID id);

	@PostMapping("/books/copies/save-all")
	public void saveAllCopies(@RequestBody List<CopyDTO> copiesDTO);

	@DeleteMapping("/books/copies/deleteAll/{title}")
	public void deleteAllCopiesByTitle(@PathVariable String title);

	@DeleteMapping("/books/copies/delete/{id}")
	public void deleteCopyById(@PathVariable UUID id);

}
