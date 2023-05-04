package com.app.pulisher.book.model;

import java.util.UUID;

import lombok.Data;

@Data
public class BookDTO {

	private String title;
	private Integer numberOfPages;
	private String genrer;
	private String author;
}
