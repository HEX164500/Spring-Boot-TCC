package com.sigen.api.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.sigen.api.entities.Usuario;
import com.sigen.api.enums.AccessLevel;
import com.sigen.api.enums.Genero;

import lombok.Getter;

@Getter
public class UsuarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String email;
	private String cpf;
	private AccessLevel acesso;
	private Genero genero;
	private Set<String> telefones;
	private Boolean ativo;
	private LocalDate registro;
	private LocalDate nascimento;

	public UsuarioDTO(Usuario usuario) {
		id = usuario.getId();
		nome = usuario.getNome();
		email = usuario.getEmail();
		cpf = usuario.getCpf();
		acesso = usuario.getAcesso();
		genero = usuario.getGenero();
		telefones = new HashSet<>(usuario.getTelefones());
		ativo = usuario.isAtivo();
		registro = usuario.getRegistro();
		nascimento = usuario.getNascimento();
	}
}
