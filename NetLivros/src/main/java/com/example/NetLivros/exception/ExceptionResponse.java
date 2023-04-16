package com.example.NetLivros.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ExceptionResponse {

	
	private String title;
	private Date timestamp;
	private String message;
	private String details;
}
