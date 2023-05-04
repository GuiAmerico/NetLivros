package com.app.pulisher.book.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CopyDTO {

	private String bookTitle;

	private LocalDateTime dateRent;

	private LocalDateTime devolutionDate;

	private LocalDateTime dateThatShouldBeReturned;

	private String devolutionCondition;

	private String CPFOfWhoRented;
}
