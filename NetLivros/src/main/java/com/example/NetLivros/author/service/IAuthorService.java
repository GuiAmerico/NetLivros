package com.example.NetLivros.author.service;

import java.util.List;
import java.util.UUID;

import com.example.NetLivros.author.model.dto.AuthorDTO;

public interface IAuthorService {

	public AuthorDTO save(AuthorDTO autorDTO);

	public List<AuthorDTO> findAll();

	public AuthorDTO findById(UUID id);

	public AuthorDTO findByName(String nome);

	public AuthorDTO update(UUID id, AuthorDTO autorDTO);

	public void deleteById(UUID id);
}
