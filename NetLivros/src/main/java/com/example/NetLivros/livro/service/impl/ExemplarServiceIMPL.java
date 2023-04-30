package com.example.NetLivros.livro.service.impl;

import static java.util.Objects.isNull;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.stereotype.Service;

import com.example.NetLivros.exception.ResourceNotFoundException;
import com.example.NetLivros.exception.ResourceUnavailableException;
import com.example.NetLivros.exception.UserNotValidException;
import com.example.NetLivros.livro.controller.ExemplarController;
import com.example.NetLivros.livro.mapper.ExemplarMapper;
import com.example.NetLivros.livro.model.Exemplar;
import com.example.NetLivros.livro.model.Livro;
import com.example.NetLivros.livro.model.dto.ExemplarDTO;
import com.example.NetLivros.livro.repository.ExemplarRepository;
import com.example.NetLivros.livro.repository.LivroRepository;
import com.example.NetLivros.livro.service.IExemplarService;
import com.example.NetLivros.livro.utils.Publisher;
import com.example.NetLivros.livro.utils.Utils;
import com.example.NetLivros.usuario.listener.UsuarioListener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExemplarServiceIMPL implements IExemplarService {

	private final ExemplarRepository exemplarRepository;
	private final LivroRepository livroRepository;
	private final Clock clock;
	private final Utils utils;
	private final Publisher publisher;
	private final ExemplarMapper exemplarMapper;

	@Override
	public List<ExemplarDTO> findAllExemplares() {
		log.info("Buscando exemplares no banco de dados");
		return exemplarMapper.toExemplarDTOList(exemplarRepository.findAll());
	}

	@Override
	public ExemplarDTO save(ExemplarDTO exemplarDTO) {

		Livro livro = livroRepository.findByTitulo(exemplarDTO.getTituloLivro())
				.orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado com esse título!"));

		Exemplar exemplar = exemplarMapper.toExemplar(exemplarDTO);
		exemplarRepository.save(exemplar);
		livro.addExemplar(exemplar);

		log.info("Salvando exemplar no banco de dados");
		livroRepository.save(livro);
		return exemplarMapper.toExemplarDTO(exemplar);
	}

	@Override
	public void saveAllExemplares(List<ExemplarDTO> exemplaresDTO) {

		List<Exemplar> exemplares = exemplarMapper.toExemplarList(exemplaresDTO);
		exemplares.stream().map(exemplar -> {
			Livro livro = livroRepository.findByTitulo(exemplar.getTituloLivro())
					.orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado com esse título!"));
			livro.addExemplar(exemplar);

			return livroRepository.save(livro);

		});
		log.info("Salvando exemplares no banco de dados");

	}

	@Override
	public Object alugarExemplarByTitulo(String titulo, UsuarioListener usuarioListener) {
		if (!exemplarRepository.existsByTituloLivro(titulo)) {
			throw new ResourceNotFoundException("Livro não encontrado com esse título!");
		}
		if (getQuantidadeDisponivel(titulo) < 1) {
			usuarioListener.setLivroDeInteresse(titulo);
			publisher.add(usuarioListener);
			return """
					Exemplar indisponível,
					notificaremos quando houver disponibilidade,
					veja outros livros ou espere até que alguém devolva!
					""";
		}
		Exemplar exemplar = exemplarRepository.findExemplarByTitulo(titulo).get();
		exemplar.setDataAluguel(LocalDateTime.now(clock));

		exemplar.setDataQueDeveriaSerDevolvido(LocalDateTime.now(clock).plusDays(7));
		exemplar.setCPFDeQuemAlugou(usuarioListener.getCPF());

		log.info("Alugando exemplar");
		exemplarRepository.save(exemplar);
		return exemplar;

	}

	@Override
	public ExemplarDTO findExemplarByTitulo(String titulo) {
		Exemplar exemplar = exemplarRepository.findExemplarByTitulo(titulo)
				.orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado com esse título!"));

		final String LINK = String.format("http://localhost:8080/api/v1/livros/exemplares/alugar/%s", titulo);
		ExemplarDTO exemplarDTO = exemplarMapper.toExemplarDTO(exemplar);
		exemplarDTO.add(linkTo(methodOn(ExemplarController.class).findExemplarByTitulo(titulo)).withRel(LINK));
		log.info("Buscando exemplar por titulo");
		return exemplarDTO;

	}

	@Override
	public Integer getQuantidadeDisponivel(String titulo) {

		log.info("Buscando quantidade disponivel de exemplar por titulo");
		return exemplarRepository.getQuantidadeDisponivel(titulo);
	}

	@Override
	public String devolverExemplar(UUID id, UsuarioListener usuarioListener) {

		Exemplar exemplar = exemplarRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado!"));

		if (!exemplar.getCPFDeQuemAlugou().equals(usuarioListener.getCPF())) {
			throw new UserNotValidException("CPF do desse usuário não condiz com o usuário que alugou esse exemplar!");
		}
		if (isNull(exemplar.getDataAluguel())) {
			throw new ResourceUnavailableException("Não é possivel devolver um livro que já foi devolvido!");
		}
		exemplar.setCPFDeQuemAlugou(null);
		exemplar.setDataAluguel(null);

		exemplar.setDataDevolucao(LocalDateTime.now(clock));
		System.out.println(exemplar);
		BigDecimal aluguel = utils.verificarAluguel(exemplar).setScale(2);

		exemplar.setDataQueDeveriaSerDevolvido(null);
		exemplar.setDataDevolucao(null);

		log.info("Devolvendo exemplar");
		exemplarRepository.save(exemplar);

		publisher.notificarObservers(exemplar.getTituloLivro());
		publisher.remove(usuarioListener);
		return String.format(
				"O valor do seu aluguel, levando em consideração o estado do livro e data de devolução é de: R$%s",
				aluguel);

	}

	@Override
	public void deleteAllExemplaresByTitulo(String titulo) {
		log.info("Deletando todos exemplares por titulo do banco de dados");
		exemplarRepository.deleteAllByTituloLivro(titulo);
	}

	@Override
	public void deleteExemplarById(UUID id) {
		log.info("Deletando exemplar por titulo do banco de dados");
		exemplarRepository.deleteById(id);
	}

}
