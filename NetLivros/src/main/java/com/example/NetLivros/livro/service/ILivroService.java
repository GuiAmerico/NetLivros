package com.example.NetLivros.livro.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.NetLivros.livro.enums.Genero;
import com.example.NetLivros.livro.model.Exemplar;
import com.example.NetLivros.livro.model.dto.LivroDTO;

public interface ILivroService {

	LivroDTO save(UUID autorId, LivroDTO livroDTO);

	List<LivroDTO> findAll(String titulo, Integer numeroDePaginas, BigDecimal preco, Genero genero);

	List<LivroDTO> findAllByPreco(Double min, Double max);

	LivroDTO findById(UUID id);

	LivroDTO update(UUID id, LivroDTO livroDTO);

	void deleteById(UUID id);

}
