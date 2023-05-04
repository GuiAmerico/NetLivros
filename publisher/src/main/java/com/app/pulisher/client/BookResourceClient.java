package com.app.pulisher.client;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.app.pulisher.book.model.BookDTO;
import com.app.pulisher.book.model.CopyDTO;

@FeignClient(value = "msnetlivros", path = "/api/v1/books")
public interface BookResourceClient {

	@PostMapping("/{authorId}")
	public ResponseEntity<BookDTO> save(@PathVariable UUID authorId, @RequestBody BookDTO bookDTO);

	@PostMapping("/copies/save")
	public ResponseEntity<CopyDTO> save(@RequestBody CopyDTO copyDTO) ;
}
