package com.example.NetLivros.mock;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.NetLivros.book.model.Copy;
import com.example.NetLivros.book.model.dto.CopyDTO;
import com.example.NetLivros.book.model.enums.DevolutionCondition;

public class MocksCopy {

	public static final Copy COPY_1 = Copy.builder().bookTitle("Title 1").CPFOfWhoRented("05491217069")
			.dateRent(LocalDateTime.now()).dateThatShouldBeReturned(LocalDateTime.now().plusDays(7))
			.devolutionDate(LocalDateTime.now().plusDays(3)).devolutionCondition(DevolutionCondition.BAD).build();
	public static final Copy COPY_2 = Copy.builder().bookTitle("Title 2").CPFOfWhoRented("79017482060")
			.dateRent(LocalDateTime.now()).dateThatShouldBeReturned(LocalDateTime.now().plusDays(7))
			.devolutionDate(LocalDateTime.now().plusDays(3)).devolutionCondition(DevolutionCondition.VERY_BAD).build();
	public static final Copy COPY_3 = Copy.builder().bookTitle("Title 3").CPFOfWhoRented("27735371063")
			.dateRent(LocalDateTime.now()).dateThatShouldBeReturned(LocalDateTime.now().plusDays(7))
			.devolutionDate(LocalDateTime.now().plusDays(3)).devolutionCondition(DevolutionCondition.GOOD).build();
	public static Copy INVALID_COPY = Copy.builder().bookTitle(null).CPFOfWhoRented(null).dateRent(null)
			.dateThatShouldBeReturned(null).devolutionDate(null).devolutionCondition(null).build();

	public static List<Copy> COPIES = new ArrayList<Copy>() {
		private static final long serialVersionUID = 1L;
		{
			add(COPY_1);
			add(COPY_2);
			add(COPY_3);
		}
	};
	public static CopyDTO COPY_DTO_1 = CopyDTO.builder().bookTitle("Title 1").CPFOfWhoRented("05491217069")
			.dateRent(LocalDateTime.now()).dateThatShouldBeReturned(LocalDateTime.now().plusDays(7))
			.devolutionDate(LocalDateTime.now().plusDays(3)).devolutionCondition(DevolutionCondition.BAD).build();
	public static CopyDTO COPY_DTO_2 = CopyDTO.builder().bookTitle("Title 2").CPFOfWhoRented("79017482060")
			.dateRent(LocalDateTime.now()).dateThatShouldBeReturned(LocalDateTime.now().plusDays(7))
			.devolutionDate(LocalDateTime.now().plusDays(3)).devolutionCondition(DevolutionCondition.VERY_BAD).build();
	public static CopyDTO COPY_DTO_3 = CopyDTO.builder().bookTitle("Title 3").CPFOfWhoRented("27735371063")
			.dateRent(LocalDateTime.now()).dateThatShouldBeReturned(LocalDateTime.now().plusDays(7))
			.devolutionDate(LocalDateTime.now().plusDays(3)).devolutionCondition(DevolutionCondition.GOOD).build();
	public static CopyDTO INVALID_COPY_DTO = CopyDTO.builder().bookTitle(null).CPFOfWhoRented(null).dateRent(null)
			.dateThatShouldBeReturned(null).devolutionDate(null).devolutionCondition(null).build();
	public static List<CopyDTO> COPIES_DTO = new ArrayList<CopyDTO>() {
		private static final long serialVersionUID = 1L;
		{
			add(COPY_DTO_1);
			add(COPY_DTO_2);
			add(COPY_DTO_3);
		}
	};
}
