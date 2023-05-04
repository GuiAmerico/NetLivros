package com.example.NetLivros.book.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.NetLivros.book.model.Book;

public interface BookRepository extends JpaRepository<Book, UUID> {
	List<Book> findByAuthor(String author);

	Boolean existsByTitle(String title);

	Optional<Book> findByTitle(String title);

	

}
