package com.example.NetLivros.autor.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.NetLivros.autor.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, UUID> {

	Autor findByNome(String autor);
}
