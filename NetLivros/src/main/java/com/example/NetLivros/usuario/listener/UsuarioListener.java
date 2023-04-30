package com.example.NetLivros.usuario.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.NetLivros.livro.utils.Observer;
import com.example.NetLivros.service.EmailService;
import com.example.NetLivros.service.impl.EmailServiceIMPL;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsuarioListener implements Observer {

	private String CPF;
	private String email;

	@JsonIgnore
	private String livroDeInteresse;
	@JsonIgnore
	private EmailServiceIMPL emailService = new EmailServiceIMPL(new JavaMailSenderImpl());

	@Override
	public void update(String tituloLivro) {
		emailService.enviarEmail(this.email, tituloLivro);
		

	}

	

}
