package com.example.NetLivros.author.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.NetLivros.author.model.Author;

public interface AuthorRepository extends JpaRepository<Author, UUID> {

	Optional<Author> findByName(String author);
}
