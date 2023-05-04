package com.app.pulisher.publisher.service;

import java.util.UUID;

import com.app.pulisher.book.model.BookDTO;
import com.app.pulisher.book.model.BookSituation;
import com.app.pulisher.book.model.CopyDTO;

public interface IPubisherService {

	BookSituation bookReview(UUID id,BookDTO book);
	CopyDTO saveCopy(CopyDTO copyDTO);
}
