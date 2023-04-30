package com.example.NetLivros.livro.service;

import java.util.List;
import java.util.UUID;

import com.example.NetLivros.livro.model.dto.ExemplarDTO;
import com.example.NetLivros.usuario.listener.UsuarioListener;

public interface IExemplarService {

	List<ExemplarDTO> findAllExemplares();

	ExemplarDTO save(ExemplarDTO exemplarDTO);

	ExemplarDTO findExemplarByTitulo(String titulo);

	void saveAllExemplares(List<ExemplarDTO> exemplares);

	Object alugarExemplarByTitulo(String titulo, UsuarioListener usuarioListener);

	Integer getQuantidadeDisponivel(String titulo);

	String devolverExemplar(UUID id, UsuarioListener usuarioListener);

	void deleteAllExemplaresByTitulo(String titulo);

	void deleteExemplarById(UUID id);
}
