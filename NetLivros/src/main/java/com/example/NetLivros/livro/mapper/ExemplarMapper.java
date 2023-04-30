package com.example.NetLivros.livro.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.NetLivros.livro.model.Exemplar;
import com.example.NetLivros.livro.model.dto.ExemplarDTO;

@Service
public class ExemplarMapper {

	public ExemplarDTO toExemplarDTO(Exemplar exemplar) {

		return new ExemplarDTO(exemplar);
	}

	public Exemplar toExemplar(ExemplarDTO exemplarDTO) {
		Exemplar exemplar = new Exemplar();
		exemplar.setCondicaoDevolucao(exemplarDTO.getCondicaoDevolucao());
		exemplar.setCPFDeQuemAlugou(exemplarDTO.getCPFDeQuemAlugou());
		exemplar.setDataAluguel(exemplarDTO.getDataAluguel());
		exemplar.setDataDevolucao(exemplarDTO.getDataDevolucao());
		exemplar.setDataQueDeveriaSerDevolvido(exemplarDTO.getDataQueDeveriaSerDevolvido());
		exemplar.setId(exemplarDTO.getId());
//		exemplar.setQuantidadeDisponivel(exemplarDTO.getQuantidadeDisponivel());
		exemplar.setTituloLivro(exemplarDTO.getTituloLivro());
		return exemplar;
	}

	public List<Exemplar> toExemplarList(List<ExemplarDTO> exemplaresDTO) {

		return exemplaresDTO.stream().map(exemplarDTO -> toExemplar(exemplarDTO)).toList();
	}

	public List<ExemplarDTO> toExemplarDTOList(List<Exemplar> exemplares) {
		return exemplares.stream().map(exemplar -> toExemplarDTO(exemplar)).toList();
	}

}
