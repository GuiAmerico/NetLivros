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

	public void notificarObservers(String tituloLivro) {

		observers.stream().filter(observer -> {
			if (observer instanceof UsuarioListener) {
				if (((UsuarioListener) observer).getLivroDeInteresse().equals(tituloLivro)) {
					return true;
				}
			}
			return false;
		}).forEach(observer -> observer.update(tituloLivro));
	}

}
