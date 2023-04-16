package com.example.NetLivros.livro.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.example.NetLivros.livro.enums.Genero;
import com.example.NetLivros.livro.model.dto.LivroDTO;

public interface ILivroService {

	public LivroDTO save(UUID autorId, LivroDTO livroDTO);

	public List<LivroDTO> findAll(String titulo, Integer numeroDePaginas, BigDecimal preco, Genero genero);

	public List<LivroDTO> findAllByPreco(Double min, Double max);

	public LivroDTO findById(UUID id);

	public LivroDTO update(UUID id, LivroDTO livroDTO);

	public void deleteById(UUID id);

}
