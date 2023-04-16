package com.example.NetLivros.autor.service;

import java.util.List;
import java.util.UUID;

import com.example.NetLivros.autor.model.dto.AutorDTO;

public interface IAutorService {

	public AutorDTO save(AutorDTO autorDTO);

	public List<AutorDTO> findAll();

	public AutorDTO findById(UUID id);

	public AutorDTO findByNome(String nome);

	public AutorDTO update(UUID id, AutorDTO autorDTO);

	public void deleteById(UUID id);
}
