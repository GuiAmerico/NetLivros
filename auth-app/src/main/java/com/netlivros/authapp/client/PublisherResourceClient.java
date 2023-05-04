package com.netlivros.authapp.client;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.netlivros.authapp.dto.BookDTO;
import com.netlivros.authapp.dto.CopyDTO;

@FeignClient(value = "mspublisher", path = "/api/v1/")
public interface PublisherResourceClient {

	@PostMapping("/books/copies/save")
	public ResponseEntity<CopyDTO> save(@RequestBody CopyDTO copyDTO);

	@PostMapping("/books/{authorId}")
	public ResponseEntity<BookDTO> save(@PathVariable UUID authorId, @RequestBody @Valid BookDTO bookDTO);

}
