package com.example.NetLivros.livro.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.NetLivros.usuario.listener.UsuarioListener;

@Service
public class Publisher {
	private List<Observer> observers = new ArrayList<>();

	public void add(Observer observer) {
		this.observers.add(observer);
	}

	public void remove(Observer observer) {
		this.observers.remove(observer);
	}

	public void notifyObservers(String bookTitle) {

		observers.stream().filter(observer -> {
			if (observer instanceof UsuarioListener) {
				if (((UsuarioListener) observer).getBookOfInterest().equals(bookTitle)) {
					return true;
				}
			}
			return false;
		}).forEach(observer -> observer.update(bookTitle));
	}

}
