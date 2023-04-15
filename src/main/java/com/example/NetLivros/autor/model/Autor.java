package com.example.NetLivros.autor.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.example.NetLivros.livro.model.Livro;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Autor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private UUID id;
	@Column(length = 50, nullable = false, unique = true)
	private String nome;
	@OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
	private List<Livro> livros;

	
}
