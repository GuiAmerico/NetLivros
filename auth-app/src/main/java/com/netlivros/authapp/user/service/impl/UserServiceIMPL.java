package com.netlivros.authapp.user.service.impl;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.netlivros.authapp.client.PublisherResourceClient;
import com.netlivros.authapp.client.UserResourceClient;
import com.netlivros.authapp.dto.AuthorDTO;
import com.netlivros.authapp.dto.BookDTO;
import com.netlivros.authapp.dto.CopyDTO;
import com.netlivros.authapp.dto.UserListener;
import com.netlivros.authapp.user.UserRepository;
import com.netlivros.authapp.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceIMPL implements UserService {

	private final UserResourceClient userResourceClient;
	private final PublisherResourceClient publisherResourceClient;
	private final UserRepository userRepository;
	
	@Override
	public AuthorDTO update(UUID id, AuthorDTO author) {

		return userResourceClient.update(id, new AuthorDTO(author.name())).getBody();
	}

	@Override
	public void deleteById(UUID id) {
		userRepository.deleteById(id);
		userResourceClient.deleteById(id);
	}

	@Override
	public BookDTO save(UUID authorId, BookDTO bookDTO) {
		return publisherResourceClient.save(authorId, bookDTO).getBody();
	}

	@Override
	public BookDTO update(UUID id, @Valid BookDTO bookDTO) {
		return userResourceClient.update(id, bookDTO).getBody();
	}

	@Override
	public CopyDTO save(CopyDTO copyDTO) {
		return publisherResourceClient.save(copyDTO).getBody();
	}

	@Override
	public void saveAllCopies(List<CopyDTO> copiesDTO) {
		userResourceClient.saveAllCopies(copiesDTO);
	}

	@Override
	public void deleteAllCopiesByTitle(String title) {
		userResourceClient.deleteAllCopiesByTitle(title);

	}

	@Override
	public void deleteCopyById(UUID id) {
		userResourceClient.deleteById(id);

	}

	@Override
	public Object rentCopyByTitle(String title, UserListener userListener) {
		
		return userResourceClient.rentCopyByTitle(title, userListener).getBody();
	}

	@Override
	public String returnCopy(UUID id, UserListener userListener) {
		return userResourceClient.returnCopy(id, userListener).getBody();
	}

}
