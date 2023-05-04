package com.example.NetLivros.book.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.example.NetLivros.book.model.enums.DevolutionCondition;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Copy {

	@Id
	@GeneratedValue
	private UUID id;
	
	@Column(nullable = false)
	private String bookTitle;
	private LocalDateTime dateRent;
	private LocalDateTime devolutionDate;
	private LocalDateTime dateThatShouldBeReturned;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private DevolutionCondition devolutionCondition;
	private String CPFOfWhoRented;
}
