package com.example.NetLivros.livro.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.NetLivros.livro.model.enums.CondicaoDevolucao;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Exemplar {

	@Id
	@GeneratedValue
	private UUID id;
	
	@Column(nullable = false)
	private String tituloLivro;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm")
	private LocalDateTime dataAluguel;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm")
	private LocalDateTime dataDevolucao;

	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm")
	private LocalDateTime dataQueDeveriaSerDevolvido;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private CondicaoDevolucao condicaoDevolucao;
	
	private String CPFDeQuemAlugou;
}
