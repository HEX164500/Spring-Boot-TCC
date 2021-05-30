package com.sigen.api.services.token;

import java.io.Serializable;

import com.sigen.api.entities.Usuario;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TokenUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	private String email;
	private Long id;

	public TokenUsuario(Usuario usuario) {
		if (usuario.getEmail() == null || usuario.getId() == null)
			throw new IllegalArgumentException("Usuario invalido para construção de token");

		this.email = usuario.getEmail();
		this.id = usuario.getId();
	}
}
