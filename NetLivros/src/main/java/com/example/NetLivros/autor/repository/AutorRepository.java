package com.example.NetLivros.autor.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.NetLivros.autor.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, UUID> {

	Optional<Autor> findByNome(String autor);
}
