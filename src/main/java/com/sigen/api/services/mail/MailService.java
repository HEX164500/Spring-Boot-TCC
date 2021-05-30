package com.sigen.api.services.mail;

import com.sigen.api.entities.Usuario;

public interface MailService {

	void sendActivationToken(Usuario usuario);
}