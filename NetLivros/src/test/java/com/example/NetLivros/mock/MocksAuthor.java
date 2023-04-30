package com.example.NetLivros.mock;

import java.util.ArrayList;
import java.util.List;

import com.example.NetLivros.author.model.Author;
import com.example.NetLivros.author.model.dto.AuthorDTO;

public class MocksAuthor {

	
	public static final Author AUTHOR_1 = Author.builder().name("Author 1").build();
	public static Author AUTHOR_2 = Author.builder().name("Author 2").build();
	public static Author AUTHOR_3 = Author.builder().name("Author 3").build();
	public static Author INVALID_AUTHOR = Author.builder().name("").build();
	
	public static List<Author> AUTHORES = new ArrayList<Author>() {
		private static final long serialVersionUID = 1L;
		{
			add(AUTHOR_1);
			add(AUTHOR_2);
			add(AUTHOR_3);
		}
	};
	public static AuthorDTO AUTHOR_DTO_1 = AuthorDTO.builder().name("Author DTO 1").build();
	public static AuthorDTO AUTHOR_DTO_2 = AuthorDTO.builder().name("Author DTO 2").build();
	public static AuthorDTO AUTHOR_DTO_3 = AuthorDTO.builder().name("Author DTO 3").build();
	public static AuthorDTO INVALID_AUTHOR_DTO = AuthorDTO.builder().name("").build();
	public static List<AuthorDTO> AUTHORES_DTO = new ArrayList<AuthorDTO>(){
		private static final long serialVersionUID = 1L;
		{
			add(AUTHOR_DTO_1);
			add(AUTHOR_DTO_2);
			add(AUTHOR_DTO_3);
		}
	};;
}
