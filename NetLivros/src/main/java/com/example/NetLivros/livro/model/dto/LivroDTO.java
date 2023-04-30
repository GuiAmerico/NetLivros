package com.example.NetLivros.livro.model.dto;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.NetLivros.livro.enums.Genero;
import com.example.NetLivros.livro.model.Livro;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class LivroDTO {

	private UUID id;
	@NotBlank
	private String titulo;
	@NotNull
	private Integer numeroDePaginas;
	@NotNull
	private BigDecimal preco;
	@Enumerated(EnumType.STRING)
	private Genero genero;
	@NotNull
	private String autor;

	public LivroDTO(Livro livro) {
		this.id = livro.getId();
		this.titulo = livro.getTitulo();
		this.numeroDePaginas = livro.getNumeroDePaginas();
		this.preco = livro.getPreco();
		this.genero = livro.getGenero();
		this.autor = livro.getAutor().getNome();
	}

}
