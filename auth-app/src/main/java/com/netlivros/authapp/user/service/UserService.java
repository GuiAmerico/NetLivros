package com.netlivros.authapp.user.service;

import java.util.List;
import java.util.UUID;

import com.netlivros.authapp.dto.AuthorDTO;
import com.netlivros.authapp.dto.BookDTO;
import com.netlivros.authapp.dto.CopyDTO;
import com.netlivros.authapp.dto.UserListener;

public interface UserService {

	public AuthorDTO update(UUID id, AuthorDTO author);

	public void deleteById(UUID id);

	public BookDTO save(UUID authorId, BookDTO bookDTO);

	public BookDTO update(UUID id, BookDTO bookDTO);

	public CopyDTO save(CopyDTO copyDTO);

	public void saveAllCopies(List<CopyDTO> copiesDTO);

	public void deleteAllCopiesByTitle(String title);

	public void deleteCopyById(UUID id);
	
	Object rentCopyByTitle(String title, UserListener userListener);

	String returnCopy(UUID id, UserListener userListener);

}
