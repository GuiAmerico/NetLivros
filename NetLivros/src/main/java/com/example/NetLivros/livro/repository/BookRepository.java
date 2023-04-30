package com.example.NetLivros.livro.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.NetLivros.livro.model.Book;

public interface BookRepository extends JpaRepository<Book, UUID> {
	List<Book> findByAuthor(String author);

	Boolean existsByTitle(String title);

	Optional<Book> findByTitle(String title);

	

}
