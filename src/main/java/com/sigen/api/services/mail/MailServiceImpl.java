package com.sigen.api.services.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.sigen.api.entities.Usuario;
import com.sigen.api.services.token.TokenBuilder;
import com.sigen.api.services.token.TokenUsuario;

@Component
public class MailServiceImpl implements MailService {

	@Autowired
	private JavaMailSender sender;

	private static final String activationUrlBase = "https://sigen-api.herokuapp.com/usuarios/ativar/token/";

	@Override
	public void sendActivationToken(Usuario usuario) {

		if (usuario == null)
			throw new IllegalArgumentException("Usuario invalido");

		TokenUsuario userToken = new TokenUsuario(usuario);
		String token = TokenBuilder.encode(userToken);

		String activationUrl = activationUrlBase + token;

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(usuario.getEmail());
		mail.setFrom(MailSender.getSenderUser());
		mail.setSubject("Ativação de conta");
		mail.setText("Olá e bem vindo a Sigen, você esta a poucos passos de ativar sua nova conta !\n"
				+ "Clique neste link para ativar sua conta : " + activationUrl + "\n ");

		sender.send(mail);
	}
}
