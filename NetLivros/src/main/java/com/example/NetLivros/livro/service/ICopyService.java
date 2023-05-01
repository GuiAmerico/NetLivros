package com.example.NetLivros.livro.service;

import java.util.List;
import java.util.UUID;

import com.example.NetLivros.livro.model.dto.CopyDTO;
import com.example.NetLivros.user.listener.UserListener;

public interface ICopyService {

	List<CopyDTO> findAllCopies();

	CopyDTO save(CopyDTO copyDTO);

	CopyDTO findCopyByTitle(String title);

	void saveAllCopies(List<CopyDTO> copies);

	Object rentCopyByTitle(String title, UserListener userListener);

	Integer getAvailableQuantity(String title);

	String returnCopy(UUID id, UserListener userListener);

	void deleteAllCopiesByTitle(String titulo);

	void deleteCopyById(UUID id);
}
