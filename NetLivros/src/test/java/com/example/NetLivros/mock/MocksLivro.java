package com.example.NetLivros.mock;

import static com.example.NetLivros.mock.MocksAutor.AUTOR_1;
import static com.example.NetLivros.mock.MocksAutor.AUTOR_2;
import static com.example.NetLivros.mock.MocksAutor.AUTOR_3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.example.NetLivros.livro.enums.Genero;
import com.example.NetLivros.livro.model.Livro;
import com.example.NetLivros.livro.model.dto.LivroDTO;

public class MocksLivro {

	public static final Livro LIVRO_1 = Livro.builder().titulo("Titulo 1").genero(Genero.AUTOAJUDA)
			.numeroDePaginas(100).preco(new BigDecimal("100.0")).autor(AUTOR_1).build();
	public static final Livro LIVRO_2 = Livro.builder().titulo("Titulo 2").genero(Genero.AVENTURA)
			.numeroDePaginas(200).preco(new BigDecimal("200.0")).autor(AUTOR_2).build();
	public static final Livro LIVRO_3 = Livro.builder().titulo("Titulo 3").genero(Genero.COMEDIA)
			.numeroDePaginas(300).preco(new BigDecimal("300.0")).autor(AUTOR_3).build();
	public static Livro INVALID_LIVRO = Livro.builder().titulo("").genero(null).numeroDePaginas(null)
			.preco(null).autor(null).build();

	public static List<Livro> LIVROS = new ArrayList<Livro>() {
		private static final long serialVersionUID = 1L;
		{
			add(LIVRO_1);
			add(LIVRO_2);
			add(LIVRO_3);
		}
	};
	public static LivroDTO LIVRO_DTO_1 = LivroDTO.builder().titulo("Titulo 1").genero(Genero.AUTOAJUDA)
			.numeroDePaginas(100).preco(new BigDecimal("100.0")).autor(AUTOR_1.getNome()).build();
	public static LivroDTO LIVRO_DTO_2 = LivroDTO.builder().titulo("Titulo 2").genero(Genero.AVENTURA)
			.numeroDePaginas(200).preco(new BigDecimal("200.0")).autor(AUTOR_2.getNome()).build();
	public static LivroDTO LIVRO_DTO_3 = LivroDTO.builder().titulo("Titulo 3").genero(Genero.COMEDIA)
			.numeroDePaginas(300).preco(new BigDecimal("100.0")).autor(AUTOR_3.getNome()).build();
	public static LivroDTO INVALID_LIVRO_DTO = LivroDTO.builder().titulo("").genero(null)
			.numeroDePaginas(null).preco(null).autor(null).build();
	public static List<LivroDTO> LIVROS_DTO = new ArrayList<LivroDTO>() {
		private static final long serialVersionUID = 1L;
		{
			add(LIVRO_DTO_1);
			add(LIVRO_DTO_2);
			add(LIVRO_DTO_3);
		}
	};
}
