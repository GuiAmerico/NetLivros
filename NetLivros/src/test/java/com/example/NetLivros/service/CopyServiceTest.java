package com.example.NetLivros.service;

import static com.example.NetLivros.mock.MocksCopy.COPY_1;
import static com.example.NetLivros.mock.MocksCopy.COPY_3;
import static com.example.NetLivros.mock.MocksCopy.COPY_DTO_1;
import static com.example.NetLivros.mock.MocksCopy.COPY_DTO_2;
import static com.example.NetLivros.mock.MocksCopy.COPY_DTO_3;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.NetLivros.book.mapper.CopyMapper;
import com.example.NetLivros.book.model.Copy;
import com.example.NetLivros.book.model.Message;
import com.example.NetLivros.book.model.dto.CopyDTO;
import com.example.NetLivros.book.repository.BookRepository;
import com.example.NetLivros.book.repository.CopyRepository;
import com.example.NetLivros.book.service.impl.CopyServiceIMPL;
import com.example.NetLivros.book.utils.Publisher;
import com.example.NetLivros.book.utils.Utils;
import com.example.NetLivros.email.service.impl.EmailServiceIMPL;
import com.example.NetLivros.exception.ResourceNotFoundException;
import com.example.NetLivros.exception.ResourceUnavailableException;
import com.example.NetLivros.exception.UserNotValidException;
import com.example.NetLivros.mock.MocksBooks;
import com.example.NetLivros.mock.MocksCopy;
import com.example.NetLivros.user.listener.UserListener;

@ExtendWith(MockitoExtension.class)
class CopyServiceTest {

	@InjectMocks
	CopyServiceIMPL copyService;
	@Mock
	CopyRepository copyRepository;
	@Mock
	BookRepository bookRepository;
	@Mock
	Clock clock;
	@Mock
	Utils utils;
	@Mock
	Publisher publisher;
	@Mock
	CopyMapper copyMapper;
	@Mock
	UserListener userListener;
	@Mock
	EmailServiceIMPL emailServiceIMPL;
	Copy copy;

	private static ZonedDateTime NOW = ZonedDateTime.of(2023, 5, 01, 11, 20, 00, 0, ZoneId.of("UTC"));

	@BeforeEach
	void setup() {
		userListener = new UserListener();
		copy = new Copy();
	}

	@Test
	void testFindAllCopies() {
		when(copyMapper.toCopyDTOList(MocksCopy.COPIES)).thenReturn(MocksCopy.COPIES_DTO);
		when(copyRepository.findAll()).thenReturn(MocksCopy.COPIES);
		
		List<CopyDTO> copies = copyService.findAllCopies();
		
		assertThat(copies).hasSize(3);
		assertThat(copies.get(0)).isEqualTo(COPY_DTO_1);
		assertThat(copies.get(1)).isEqualTo(COPY_DTO_2);
		assertThat(copies.get(2)).isEqualTo(COPY_DTO_3);
	}

	@Test
	void testSave_BookNotFound() {
		assertThatThrownBy(() -> copyService.save(COPY_DTO_1)).isInstanceOf(ResourceNotFoundException.class);
	}

	@Test
	void testSave() {
		when(bookRepository.findByTitle(anyString())).thenReturn(Optional.of(MocksBooks.BOOK_1));
		when(copyMapper.toCopyDTO(COPY_1)).thenReturn(MocksCopy.COPY_DTO_1);
		when(copyMapper.toCopy(MocksCopy.COPY_DTO_1)).thenReturn(COPY_1);
		when(copyRepository.save(COPY_1)).thenReturn(COPY_1);
		
		CopyDTO copyDTO = copyService.save(COPY_DTO_1);
		
		assertThat(copyDTO).isEqualTo(COPY_DTO_1);
	}

	@Test
	void testSaveAllCopies() {
		when(copyMapper.toCopyList(MocksCopy.COPIES_DTO)).thenReturn(MocksCopy.COPIES);
		
		assertDoesNotThrow(() -> copyService.saveAllCopies(MocksCopy.COPIES_DTO));
		
	
	}

	@Test
	void testRentCopyByTitle_BookNotFound() {
		assertThatThrownBy(() -> copyService.rentCopyByTitle("Title", userListener))
				.isInstanceOf(ResourceNotFoundException.class);
	}

	@Test
	void testRentCopyByTitle_BookNotAvailable() {
		when(copyRepository.existsByBookTitle(anyString())).thenReturn(true);
		Object message = copyService.rentCopyByTitle("Title", userListener);
		assertThat(message).isEqualTo("""
				Cópia indisponível,
				notificaremos quando houver disponibilidade,
				veja outros livros ou espere até que alguém devolva!
				""");
	}

	@Test
	void testRentCopyByTitle() {
		when(copyRepository.existsByBookTitle(anyString())).thenReturn(true);
		when(copyRepository.getAvailableQuantity(anyString())).thenReturn(1);
		when(copyRepository.findCopyByTitle(anyString())).thenReturn(Optional.of(COPY_1));
		when(clock.getZone()).thenReturn(NOW.getZone());
		when(clock.instant()).thenReturn(NOW.toInstant());

		Object copy = copyService.rentCopyByTitle("Title", userListener);
		assertThat(copy).isEqualTo(COPY_1);
	}

	@Test
	void testFindCopyByTitle() {
		when(copyRepository.findCopyByTitle(anyString())).thenReturn(Optional.of(COPY_1));
		when(copyMapper.toCopyDTO(COPY_1)).thenReturn(MocksCopy.COPY_DTO_1);
		
		CopyDTO copyDTO = copyService.findCopyByTitle("Title 1");

		assertThat(copyDTO).isEqualTo(COPY_DTO_1);
	}

	@Test
	void testReturnCopy_BookNotFound() {
		assertThatThrownBy(() -> copyService.rentCopyByTitle("Title", userListener))
				.isInstanceOf(ResourceNotFoundException.class);

	}

	@Test
	void testReturnCopy_UserCPFNotMatchesCopyCPF() {
		UUID id = UUID.randomUUID();
		when(copyRepository.findById(id)).thenReturn(Optional.of(COPY_1));
		userListener.setCPF("123456");

		assertThatThrownBy(() -> copyService.returnCopy(id, userListener)).isInstanceOf(UserNotValidException.class);

	}

	@Test
	void testReturnCopy_CopyAlreadyReturn() {
		UUID id = UUID.randomUUID();
		when(copyRepository.findById(id)).thenReturn(Optional.of(COPY_3));

		userListener.setCPF("27735371063");
		COPY_3.setDateRent(null);
		assertThatThrownBy(() -> copyService.returnCopy(id, userListener))
				.isInstanceOf(ResourceUnavailableException.class);

	}

	@Test
	void testReturnCopy() {
		UUID id = UUID.randomUUID();
		when(copyRepository.findById(id)).thenReturn(Optional.of(COPY_1));
		when(clock.getZone()).thenReturn(NOW.getZone());
		when(clock.instant()).thenReturn(NOW.toInstant());
		when(utils.checkRent(Mockito.any())).thenReturn(new BigDecimal("20.00"));

		userListener.setCPF("05491217069");
		Message rent = copyService.returnCopy(id, userListener);
		assertThat(rent.getMessage()).isEqualTo(
				"O valor do seu aluguel, tendo em conta o estado da livro e a data de devolução, é: R$20.00");
	}


	@Test
	void testDeleteAllCopiesByTitle() {
		assertDoesNotThrow(() -> copyService.deleteAllCopiesByTitle("Title 1"));

	}

	@Test
	void testDeleteCopyById() {
		assertDoesNotThrow(() -> copyService.deleteCopyById(UUID.randomUUID()));
	}

}
