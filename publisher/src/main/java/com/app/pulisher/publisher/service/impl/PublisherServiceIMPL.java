package com.app.pulisher.publisher.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.app.pulisher.book.model.BookDTO;
import com.app.pulisher.book.model.BookSituation;
import com.app.pulisher.book.model.CopyDTO;
import com.app.pulisher.client.BookResourceClient;
import com.app.pulisher.publisher.service.IPubisherService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PublisherServiceIMPL implements IPubisherService {

	private final BookResourceClient bookResourceClient;

	public BookSituation bookReview(UUID id, BookDTO book) {
		double random = Math.random();

		if (random < 0.50) {
			return BookSituation.REFUSED;
		}
		bookResourceClient.save(id, book);
		return BookSituation.PUBLISHED;

	}
	
	public CopyDTO saveCopy(CopyDTO copyDTO) {
		return bookResourceClient.save(copyDTO).getBody();
	}

}
