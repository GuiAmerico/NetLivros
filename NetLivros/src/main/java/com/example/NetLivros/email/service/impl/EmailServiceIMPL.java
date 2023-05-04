package com.example.NetLivros.email.service.impl;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.example.NetLivros.email.service.EmailService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmailServiceIMPL implements EmailService {

	private final JavaMailSenderImpl javaMailSenderImpl;

	private String text;

	private static final String FROM = "vousuaveagencia@gmail.com";
	private static final String SUBJECT = "O livro que você tanto queria está disponivel!";
	private static final String PASSWORD = "tmireetgdzlopaks";
	private static final String USERNAME = "vousuaveagencia@gmail.com";

	@Override
	public void enviarEmail(String email, String tituloLivro) {

		text = String.format("""
				O livro que você se interessou anteriormente já está disponivel,
				alugue-o e não perca tempo
				(Clique aqui!)=> http://localhost:8080/msauth/api/v1/books/copies/%s
				""", tituloLivro);
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);
		message.setSubject(SUBJECT);
		message.setText(text);
		message.setFrom(FROM);
		javaMailSenderImpl.setUsername(USERNAME);
		javaMailSenderImpl.setPassword(PASSWORD);
		configureJavaMailSenderImpl(javaMailSenderImpl);

		javaMailSenderImpl.send(message);

	}

	public void configureJavaMailSenderImpl(JavaMailSenderImpl javaMailSender) {
		Properties properties = new Properties();
		properties.put("spring.mail.properties.mail.smtp.auth", true);
		properties.put("spring.mail.properties.mail.smtp.starttls.enable", true);
		properties.put("spring.mail.properties.mail.smtp.auth", true);
		properties.put("spring.mail.properties.mail.smtp.starttls.enable", true);
		properties.put("mail.transport.protocol", "smtp");
		properties.setProperty("mail.transport.protocol", "smtp");
		properties.setProperty("mail.host", "smtp.gmail.com");
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.socketFactory.fallback", "false");
		javaMailSender.setPort(465);
		javaMailSender.setJavaMailProperties(properties);

	}

}
