package com.example.NetLivros.livro.model.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import com.example.NetLivros.livro.model.Exemplar;
import com.example.NetLivros.livro.model.enums.CondicaoDevolucao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ExemplarDTO extends RepresentationModel<ExemplarDTO> {

	public ExemplarDTO(Exemplar exemplar) {
		this.id = exemplar.getId();
		this.dataAluguel = exemplar.getDataAluguel();
		this.dataDevolucao = exemplar.getDataDevolucao();
		this.dataQueDeveriaSerDevolvido = exemplar.getDataQueDeveriaSerDevolvido();
//		this.quantidadeDisponivel = exemplar.getQuantidadeDisponivel();
		this.condicaoDevolucao = exemplar.getCondicaoDevolucao();
		this.tituloLivro = exemplar.getTituloLivro();
	}

	private UUID id;

	private String tituloLivro;

	private LocalDateTime dataAluguel;

	private LocalDateTime dataDevolucao;

	private LocalDateTime dataQueDeveriaSerDevolvido;

	@Enumerated(EnumType.STRING)
	private CondicaoDevolucao condicaoDevolucao;

//	private Integer quantidadeDisponivel;

	private String CPFDeQuemAlugou;
}
