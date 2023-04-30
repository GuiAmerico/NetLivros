package com.example.NetLivros.livro.service;

import java.util.List;
import java.util.UUID;

import com.example.NetLivros.livro.enums.Genrer;
import com.example.NetLivros.livro.model.dto.BookDTO;

public interface IBookService {

	BookDTO save(UUID authorId, BookDTO bookDTO);

	List<BookDTO> findAll(String title, Integer numberOfPages, Genrer genrer);

	BookDTO findById(UUID id);

	BookDTO update(UUID id, BookDTO bookDTO);

	void deleteById(UUID id);

}
