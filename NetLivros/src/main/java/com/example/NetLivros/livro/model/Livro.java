package com.example.NetLivros.livro.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.example.NetLivros.autor.model.Autor;
import com.example.NetLivros.livro.enums.Genero;
import com.example.NetLivros.livro.model.enums.SituacaoLivro;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class Livro {

	@Id
	@GeneratedValue
	private UUID id;
	@Column(length = 50, nullable = false, unique = true)
	private String titulo;
	@Column(nullable = false)
	private Integer numeroDePaginas;
	@Column(nullable = false, precision = 5, scale = 2)
	private BigDecimal preco;
	@Column(length = 50, nullable = false)
	@Enumerated(EnumType.STRING)
	private Genero genero;
	@Column
	@Enumerated(EnumType.STRING)
	private SituacaoLivro situacao;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "livro_exemplares", joinColumns = @JoinColumn(name = "livro_id", referencedColumnName = "id"))
	private List<Exemplar> exemplares;
	@ManyToOne
	private Autor autor;

	public Livro(String titulo, Integer numeroDePaginas, BigDecimal preco, Genero genero) {
		this.titulo = titulo;
		this.numeroDePaginas = numeroDePaginas;
		this.preco = preco;
		this.genero = genero;
	}

	public void addExemplar(Exemplar exemplar) {
		this.exemplares.add(exemplar);
	}

}
