package com.example.NetLivros.livro.service.impl;

import static java.util.Objects.isNull;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.NetLivros.exception.ResourceNotFoundException;
import com.example.NetLivros.exception.ResourceUnavailableException;
import com.example.NetLivros.exception.UserNotValidException;
import com.example.NetLivros.livro.controller.CopyController;
import com.example.NetLivros.livro.mapper.CopyMapper;
import com.example.NetLivros.livro.model.Book;
import com.example.NetLivros.livro.model.Copy;
import com.example.NetLivros.livro.model.dto.CopyDTO;
import com.example.NetLivros.livro.repository.BookRepository;
import com.example.NetLivros.livro.repository.CopyRepository;
import com.example.NetLivros.livro.service.ICopyService;
import com.example.NetLivros.livro.utils.Publisher;
import com.example.NetLivros.livro.utils.Utils;
import com.example.NetLivros.usuario.listener.UsuarioListener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CopyServiceIMPL implements ICopyService {

	private final CopyRepository copyRepository;
	private final BookRepository bookRepository;
	private final Clock clock;
	private final Utils utils;
	private final Publisher publisher;
	private final CopyMapper copyMapper;

	@Override
	public List<CopyDTO> findAllCopies() {
		log.info("Searching for copies in the database");
		return copyMapper.toCopyDTOList(copyRepository.findAll());
	}

	@Override
	public CopyDTO save(CopyDTO copyDTO) {

		Book book = bookRepository.findByTitle(copyDTO.getBookTitle())
				.orElseThrow(() -> new ResourceNotFoundException(""));

		Copy copy = copyMapper.toCopy(copyDTO);
		copyRepository.save(copy);
		book.addCopy(copy);

		log.info("Salvando copy no banco de dados");
		bookRepository.save(book);
		return copyMapper.toCopyDTO(copy);
	}

	@Override
	public void saveAllCopies(List<CopyDTO> copyesDTO) {

		List<Copy> copyes = copyMapper.toCopyList(copyesDTO);
		copyes.stream().map(copy -> {
			Book book = bookRepository.findByTitle(copy.getBookTitle())
					.orElseThrow(() -> new ResourceNotFoundException("Book not found with that title!"));
			book.addCopy(copy);

			return bookRepository.save(book);

		});
		log.info("Salvando copyes no banco de dados");

	}

	@Override
	public Object rentCopyByTitle(String title, UsuarioListener usuarioListener) {
		if (!copyRepository.existsByBookTitle(title)) {
			throw new ResourceNotFoundException("Book not found with that title!");
		}
		if (getAvailableQuantity(title) < 1) {
			usuarioListener.setBookOfInterest(title);
			publisher.add(usuarioListener);
			return """
					Copy indisponível,
					notificaremos quando houver disponibilidade,
					veja outros books ou espere até que alguém devolva!
					""";
		}
		Copy copy = copyRepository.findCopyByTitle(title).get();
		copy.setDateRent(LocalDateTime.now(clock));

		copy.setDateThatShouldBeReturned(LocalDateTime.now(clock).plusDays(7));
		copy.setCPFOfWhoRented(usuarioListener.getCPF());

		log.info("renting copy");
		copyRepository.save(copy);
		return copy;

	}

	@Override
	public CopyDTO findCopyByTitle(String title) {
		Copy copy = copyRepository.findCopyByTitle(title)
				.orElseThrow(() -> new ResourceNotFoundException("Book not found with that title!"));

		final String LINK = String.format("http://localhost:8080/api/v1/books/copies/rent/%s", title);
		CopyDTO copyDTO = copyMapper.toCopyDTO(copy);
		copyDTO.add(linkTo(methodOn(CopyController.class).findCopyByTitle(title)).withRel(LINK));
		log.info("Searching for copy by title");
		return copyDTO;

	}

	@Override
	public Integer getAvailableQuantity(String title) {

		log.info("Searching available amount of copy by title");
		return copyRepository.getAvailableQuantity(title);
	}

	@Override
	public String returnCopy(UUID id, UsuarioListener usuarioListener) {

		Copy copy = copyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found!"));

		if (!copy.getCPFOfWhoRented().equals(usuarioListener.getCPF())) {
			throw new UserNotValidException("This user's CPF does not match the user who rented this copy!");
		}
		if (isNull(copy.getDateRent())) {
			throw new ResourceUnavailableException(
					"It is not possible to return a book that has already been returned!");
		}
		copy.setCPFOfWhoRented(null);
		copy.setDateRent(null);

		copy.setDevolutionDate(LocalDateTime.now(clock));
		System.out.println(copy);
		BigDecimal aluguel = utils.checkRent(copy).setScale(2);

		copy.setDateThatShouldBeReturned(null);
		copy.setDevolutionDate(null);

		log.info("Returns copy");
		copyRepository.save(copy);

		publisher.notifyObservers(copy.getBookTitle());
		publisher.remove(usuarioListener);
		return String.format(
				"The value of your rent, taking into account the condition of the book and the return date, is: R$%s",
				aluguel);

	}

	@Override
	public void deleteAllCopiesByTitle(String title) {
		log.info("Deleting all copies by title from the database");
		copyRepository.deleteAllByBookTitle(title);
	}

	@Override
	public void deleteCopyById(UUID id) {
		log.info("Deleting copy by title in the database");
		copyRepository.deleteById(id);
	}

}
