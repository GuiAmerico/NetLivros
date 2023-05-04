package com.example.NetLivros.book.service;

import java.util.List;
import java.util.UUID;

import com.example.NetLivros.book.model.Message;
import com.example.NetLivros.book.model.dto.CopyDTO;
import com.example.NetLivros.user.listener.UserListener;

public interface ICopyService {

	List<CopyDTO> findAllCopies();

	CopyDTO save(CopyDTO copyDTO);

	CopyDTO findCopyByTitle(String title);

	void saveAllCopies(List<CopyDTO> copies);

	Object rentCopyByTitle(String title, UserListener userListener);

	Integer getAvailableQuantity(String title);

	Message returnCopy(UUID id, UserListener userListener);

	void deleteAllCopiesByTitle(String titulo);

	void deleteCopyById(UUID id);
}
