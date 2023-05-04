package com.example.NetLivros.book.service;

import java.util.List;
import java.util.UUID;

import com.example.NetLivros.book.model.dto.BookDTO;
import com.example.NetLivros.book.model.enums.Genrer;

public interface IBookService {

	BookDTO save(UUID authorId, BookDTO bookDTO);

	List<BookDTO> findAll(String title, Integer numberOfPages, Genrer genrer);

	BookDTO findById(UUID id);

	BookDTO update(UUID id, BookDTO bookDTO);

	void deleteById(UUID id);

}
