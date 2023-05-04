package com.example.NetLivros.book.model;

import lombok.Data;

@Data
public class Message {
	String message;

	public Message() {
	}
	private Message(String message) {
		this.message = message;
	}

	public Message sendMessage(String message, Object... args) {
		message = String.format(message, args);
		return new Message(message);
	}

}
