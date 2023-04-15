package com.example.NetLivros.autor.mapper;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.example.NetLivros.autor.model.Autor;
import com.example.NetLivros.autor.model.dto.AutorDTO;
import com.example.NetLivros.livro.model.Livro;
import com.example.NetLivros.livro.model.dto.LivroDTO;

@Component
public class AutorMapper {

	public List<AutorDTO> toAutorDTOList(List<Autor> autors) {
		return autors.stream().map(livro -> new AutorDTO(livro)).toList();
	}

	public AutorDTO toAutorDTO(Autor autor) {
		AutorDTO dto = new AutorDTO(autor);
		return dto;
	}

	public Autor toAutor(AutorDTO autorDTO) {
		Autor autor = new Autor();
		autor.setId(autorDTO.getId());
		autor.setNome(autorDTO.getNome());
		if (autorDTO.getLivrosDTO() != null) {
			List<Livro> livros = autorDTO.getLivrosDTO().stream().map(livroDTO -> {
				Livro livro = new Livro();
				BeanUtils.copyProperties(livroDTO, livro);
				livro.setAutor(autor);
				return livro;

			}).toList();
			autor.setLivros(livros);

		}

		return autor;
	}
	
	public static List<LivroDTO> verifyngAndParseToLivrosDTO(Autor autor) {
		if (autor.getLivros() != null) {
			return autor.getLivros().stream().map(livro -> new LivroDTO(livro)).toList();
		}
		return Arrays.asList();
	}

}
