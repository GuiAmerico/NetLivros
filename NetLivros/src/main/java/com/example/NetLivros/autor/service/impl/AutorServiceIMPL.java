package com.example.NetLivros.autor.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.NetLivros.autor.mapper.AutorMapper;
import com.example.NetLivros.autor.model.Autor;
import com.example.NetLivros.autor.model.dto.AutorDTO;
import com.example.NetLivros.autor.repository.AutorRepository;
import com.example.NetLivros.autor.service.IAutorService;
import com.example.NetLivros.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class AutorServiceIMPL implements IAutorService {

	private final AutorRepository autorRepository;
	private final AutorMapper mapper;

	@Override
	public AutorDTO save(AutorDTO autorDTO) {
		Autor autor = mapper.toAutor(autorDTO);
		autorRepository.save(autor);
		autorDTO = mapper.toAutorDTO(autor);
		log.info("Salvando Autor no Banco de Dados");
		return autorDTO;
	}

	@Override
	public List<AutorDTO> findAll() {
		List<Autor> autores = autorRepository.findAll();
		List<AutorDTO> autoresDTO = mapper.toAutorDTOList(autores);

		log.info("Lendo Autores do Banco de Dados");
		return autoresDTO;
	}

	@Override
	public AutorDTO findById(UUID id) {
		Autor autor = autorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Autor não encontrado!"));
		AutorDTO autorDTO = mapper.toAutorDTO(autor);

		log.info("Buscando Autor Por ID no Banco de Dados");
		return autorDTO;
	}

	@Override
	public AutorDTO update(UUID id, AutorDTO autorDTO) {
		Autor autor = autorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Autor não encontrado!"));
		autorDTO.setId(id);
		autor = mapper.toAutor(autorDTO);
		autorRepository.save(autor);

		log.info("Atualizando Autor Por ID no Banco de Dados");
		return autorDTO;
	}

	@Override
	public void deleteById(UUID id) {
		Autor autor = autorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Autor não encontrado!"));

		log.info("Deletando Autor Por ID do Banco de Dados");
		autorRepository.delete(autor);
	}

	@Override
	public AutorDTO findByNome(String nome) {
		Autor autor = autorRepository.findByNome(nome)
				.orElseThrow(() -> new ResourceNotFoundException("Autor não encontrado!"));
		AutorDTO autorDTO = mapper.toAutorDTO(autor);

		log.info("Buscando Autor Por Nome no Banco de Dados");
		return autorDTO;
	}
}
