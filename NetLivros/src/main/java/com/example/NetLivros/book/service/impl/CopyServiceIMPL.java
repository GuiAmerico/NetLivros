package com.example.NetLivros.book.service.impl;

import static java.util.Objects.isNull;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.NetLivros.book.controller.CopyController;
import com.example.NetLivros.book.mapper.CopyMapper;
import com.example.NetLivros.book.model.Book;
import com.example.NetLivros.book.model.Copy;
import com.example.NetLivros.book.model.Message;
import com.example.NetLivros.book.model.dto.CopyDTO;
import com.example.NetLivros.book.repository.BookRepository;
import com.example.NetLivros.book.repository.CopyRepository;
import com.example.NetLivros.book.service.ICopyService;
import com.example.NetLivros.book.utils.Publisher;
import com.example.NetLivros.book.utils.Utils;
import com.example.NetLivros.exception.ResourceNotFoundException;
import com.example.NetLivros.exception.ResourceUnavailableException;
import com.example.NetLivros.exception.UserNotValidException;
import com.example.NetLivros.user.listener.UserListener;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	private Message message = new Message();

	@Override
	public List<CopyDTO> findAllCopies() {
		log.info("Searching for copies in the database");
		return copyMapper.toCopyDTOList(copyRepository.findAll());
	}

	@Override
	public CopyDTO save(CopyDTO copyDTO) {

		Book book = bookRepository.findByTitle(copyDTO.getBookTitle())
				.orElseThrow(() -> new ResourceNotFoundException("Book not found!"));

		Copy copy = copyMapper.toCopy(copyDTO);
		copyRepository.save(copy);
		book.addCopy(copy);

		log.info("Saving copy in the database");
		bookRepository.save(book);
		return copyMapper.toCopyDTO(copy);
	}

	@Override
	public void saveAllCopies(List<CopyDTO> copiesDTO) {

		List<Copy> copies = copyMapper.toCopyList(copiesDTO);
		copies.stream().map(copy -> {
			Book book = bookRepository.findByTitle(copy.getBookTitle())
					.orElseThrow(() -> new ResourceNotFoundException("Book not found with that title!"));
			book.addCopy(copy);

			return bookRepository.save(book);

		});
		log.info("Saving copies in the database");

	}

	@Override
	public Object rentCopyByTitle(String title, UserListener userListener) {
		if (!copyRepository.existsByBookTitle(title)) {
			throw new ResourceNotFoundException("Book not found with that title!");
		}
		if (getAvailableQuantity(title) < 1) {
			userListener.setBookOfInterest(title);
			publisher.add(userListener);
			String txt = "Cópia indisponível,notificaremos quando houver disponibilidade,veja outros livros ou espere até que alguém devolva!";

			return message.sendMessage(txt);

		}
		Copy copy = copyRepository.findCopyByTitle(title).get();
		copy.setDateRent(LocalDateTime.now(clock));

		copy.setDateThatShouldBeReturned(LocalDateTime.now(clock).plusDays(7));
		copy.setCPFOfWhoRented(userListener.getCPF());

		log.info("Renting copy");
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
	public Message returnCopy(UUID id, UserListener userListener) {

		Copy copy = copyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found!"));

		if (isNull(copy.getDateRent())) {
			throw new ResourceUnavailableException(
					"It is not possible to return a book that has already been returned!");
		}
		if (!copy.getCPFOfWhoRented().equals(userListener.getCPF())) {
			throw new UserNotValidException("This user's CPF does not match the user who rented this copy!");
		}

		copy.setDevolutionDate(LocalDateTime.now(clock));
		BigDecimal rent = utils.checkRent(copy).setScale(2);

		copy.setCPFOfWhoRented(null);
		copy.setDateRent(null);
		copy.setDateThatShouldBeReturned(null);
		copy.setDevolutionDate(null);

		log.info("Returns copy");
		copyRepository.save(copy);

		publisher.notifyObservers(copy.getBookTitle());
		publisher.removeObservers();

		return message.sendMessage(
				"O valor do seu aluguel, tendo em conta o estado da livro e a data de devolução, é: R$%s", rent);

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
