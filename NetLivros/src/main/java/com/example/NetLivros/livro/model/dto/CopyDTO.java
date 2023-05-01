package com.example.NetLivros.livro.model.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

import org.springframework.hateoas.RepresentationModel;

import com.example.NetLivros.livro.model.Copy;
import com.example.NetLivros.livro.model.enums.DevolutionCondition;

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
public class CopyDTO extends RepresentationModel<CopyDTO> {

	public CopyDTO(Copy copy) {
		this.id = copy.getId();
		this.dateRent = copy.getDateRent();
		this.devolutionDate= copy.getDevolutionDate();
		this.dateThatShouldBeReturned = copy.getDateThatShouldBeReturned();
		this.devolutionCondition = copy.getDevolutionCondition();
		this.bookTitle = copy.getBookTitle();
	}

	private UUID id;

	@NotBlank
	private String bookTitle;

	private LocalDateTime dateRent;

	private LocalDateTime devolutionDate;

	private LocalDateTime dateThatShouldBeReturned;

	@Enumerated(EnumType.STRING)
	private DevolutionCondition devolutionCondition;


	private String CPFOfWhoRented;
}
