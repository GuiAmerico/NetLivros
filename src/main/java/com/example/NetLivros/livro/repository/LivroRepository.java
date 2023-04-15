package com.example.NetLivros.livro.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.NetLivros.livro.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, UUID> {
	List<Livro> findByAutor(String autor);
	Boolean existsByTitulo(String titulo);
	List<Livro> findByPrecoBetween(Double min, Double max);
	
}
