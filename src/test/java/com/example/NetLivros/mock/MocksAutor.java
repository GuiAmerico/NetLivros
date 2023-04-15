package com.example.NetLivros.mock;

import java.util.ArrayList;
import java.util.List;

import com.example.NetLivros.autor.model.Autor;
import com.example.NetLivros.autor.model.dto.AutorDTO;

public class MocksAutor {

	
	public static final Autor AUTOR_1 = Autor.builder().nome("Autor 1").build();
	public static Autor AUTOR_2 = Autor.builder().nome("Autor 2").build();
	public static Autor AUTOR_3 = Autor.builder().nome("Autor 3").build();
	public static Autor INVALID_AUTOR = Autor.builder().nome("").build();
	
	public static List<Autor> AUTORES = new ArrayList<Autor>() {
		private static final long serialVersionUID = 1L;
		{
			add(AUTOR_1);
			add(AUTOR_2);
			add(AUTOR_3);
		}
	};
	public static AutorDTO AUTOR_DTO_1 = AutorDTO.builder().nome("Autor DTO 1").build();
	public static AutorDTO AUTOR_DTO_2 = AutorDTO.builder().nome("Autor DTO 2").build();
	public static AutorDTO AUTOR_DTO_3 = AutorDTO.builder().nome("Autor DTO 3").build();
	public static AutorDTO INVALID_AUTOR_DTO = AutorDTO.builder().nome("").build();
	public static List<AutorDTO> AUTORES_DTO = new ArrayList<AutorDTO>(){
		private static final long serialVersionUID = 1L;
		{
			add(AUTOR_DTO_1);
			add(AUTOR_DTO_2);
			add(AUTOR_DTO_3);
		}
	};;
}
