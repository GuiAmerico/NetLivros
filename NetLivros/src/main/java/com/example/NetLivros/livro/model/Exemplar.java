package com.example.NetLivros.livro.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

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
public class Exemplar {

	@JsonIgnore
	private UUID idLivro;
	
	private String titulo;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm")
	private LocalDateTime dataAluguel;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm")
	private LocalDateTime dataDevolução;

	@Enumerated(EnumType.STRING)
	private CondicaoDevolucao condicaoDevolucao;

	private Integer quantidadeDisponivel;
}
