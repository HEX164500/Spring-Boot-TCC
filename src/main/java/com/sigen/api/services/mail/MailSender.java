package com.sigen.api.services.mail;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class MailSender extends JavaMailSenderImpl {

	@Value("${spring.mail.host}")
	private static String host;

	@Value("${spring.mail.port}")
	private static String port;

	@Value("${spring.mail.username}")
	private static String username;

	@Value("${spring.mail.password}")
	private static String password;

	@Value("${spring.mail.properties.mail.smtp.auth}")
	private static String smtpAuth;

	@Value("${spring.mail.properties.mail.smtp.starttls.enable}")
	private static String starttlsEnable;

	@Bean
	public JavaMailSender getJavaMailSender() {
		MailSender sender = new MailSender();

		if (host == null || port == null || username == null || password == null || smtpAuth == null
				|| starttlsEnable == null)
			throw new RuntimeException("Não foi possivel carregar o serviço de email");

		sender.setHost(host);
		sender.setPort(Integer.parseInt(port));
		sender.setUsername(username);
		sender.setPassword(password);

		Properties props = sender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "false");

		return sender;
	}

	protected static String getSenderUser() {
		return username;
	}

}
