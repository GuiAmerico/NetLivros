package com.app.pulisher.publisher.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pulisher.book.model.BookDTO;
import com.app.pulisher.book.model.BookSituation;
import com.app.pulisher.book.model.CopyDTO;
import com.app.pulisher.publisher.service.IPubisherService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/publisher")
public class PublisherController {

	private final IPubisherService publisherService;
	
	@PostMapping("/book-review/{id}")
	public ResponseEntity<BookSituation> bookReview(@PathVariable UUID id,@RequestBody BookDTO book){
		return ResponseEntity.status(HttpStatus.OK).body(publisherService.bookReview(id,book));
	}
	@PostMapping("/save-copies/save")
	public ResponseEntity<CopyDTO> saveCopy(@RequestBody CopyDTO copy){
		return ResponseEntity.status(HttpStatus.OK).body(publisherService.saveCopy(copy));
	}
}
