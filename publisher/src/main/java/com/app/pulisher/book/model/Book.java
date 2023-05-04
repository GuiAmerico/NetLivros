package com.app.pulisher.book.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Book {

	@Id
	@GeneratedValue
	private UUID id;
	private String title;
	private Integer numberOfPages;
	private String genrer;
	private String author;
}
