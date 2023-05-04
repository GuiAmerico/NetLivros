package com.example.NetLivros.book.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.NetLivros.book.model.Copy;
import com.example.NetLivros.book.model.dto.CopyDTO;

@Service
public class CopyMapper {

	public CopyDTO toCopyDTO(Copy copy) {

		return new CopyDTO(copy);
	}

	public Copy toCopy(CopyDTO copyDTO) {
		Copy copy = new Copy();
		copy.setDevolutionCondition(copyDTO.getDevolutionCondition());
		copy.setCPFOfWhoRented(copyDTO.getCPFOfWhoRented());
		copy.setDateRent(copyDTO.getDateRent());
		copy.setDevolutionDate(copyDTO.getDevolutionDate());
		copy.setDateThatShouldBeReturned(copyDTO.getDateThatShouldBeReturned());
		copy.setId(copyDTO.getId());
		copy.setBookTitle(copyDTO.getBookTitle());
		return copy;
	}

	public List<Copy> toCopyList(List<CopyDTO> copyesDTO) {

		return copyesDTO.stream().map(copyDTO -> toCopy(copyDTO)).toList();
	}

	public List<CopyDTO> toCopyDTOList(List<Copy> copyes) {
		return copyes.stream().map(copy -> toCopyDTO(copy)).toList();
	}

}
