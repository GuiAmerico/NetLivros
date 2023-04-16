package com.example.NetLivros.autor.model.dto;

import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotBlank;

import com.example.NetLivros.autor.mapper.AutorMapper;
import com.example.NetLivros.autor.model.Autor;
import com.example.NetLivros.livro.model.dto.LivroDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data 
public class AutorDTO {

	private UUID id;
	@NotBlank
	private String nome;
	private List<LivroDTO> livrosDTO;

	public AutorDTO(Autor autor) {
		this.id = autor.getId();
		this.nome = autor.getNome();
		this.livrosDTO = AutorMapper.verifyngAndParseToLivrosDTO(autor);
	}
}
