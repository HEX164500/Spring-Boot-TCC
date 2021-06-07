package com.sigen.api.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.sigen.api.enums.Genero;
import com.sigen.api.enums.NivelDeAcesso;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "usuarios")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@Column(nullable = false, length = 64)
	private String nome;

	@Column(nullable = false, length = 32, updatable = false)
	private String senha;

	@Column(nullable = false, length = 126, unique = true, updatable = false)
	private String email;

	@Column(nullable = false, length = 11, unique = true)
	private String cpf;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, updatable = false)
	@JsonProperty(access = Access.READ_ONLY)
	private NivelDeAcesso acesso = NivelDeAcesso.USUARIO;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Genero genero = Genero.OUTRO;

	@ElementCollection(fetch = FetchType.EAGER)
	private final Set<String> telefones = new HashSet<>();

	@JsonProperty(access = Access.READ_ONLY)
	@Column(updatable = false)
	private Boolean ativo = false;

	@Column(updatable = false)
	@JsonProperty(access = Access.READ_ONLY)
	private LocalDate registro = LocalDate.now();

	@Column(nullable = false)
	private LocalDate nascimento;

	public Usuario(Long id) {
		this.id = id;
	}

	public void setSenha(String senha) {
		if (senha.length() < 8 || senha.length() > 32)
			throw new IllegalArgumentException("Senha deve ter entre 8 e 32 caracteres");
		this.senha = senha;
	}

	public void setEmail(String email) {
		if (email.length() > 126)
			throw new IllegalArgumentException("Email deve ter no máximo 126 caracteres");
		this.email = email;
	}

	public void setCpf(String cpf) {
		try {
			Long.parseLong(cpf);
		} catch (Exception e) {
			throw new IllegalArgumentException("CPF deve conter apenas numeros");
		}
		if (cpf.length() != 11)
			throw new IllegalArgumentException("CPF deve 11 digitos");

		this.cpf = cpf;
	}

	public Boolean isAtivo() {
		return ativo;
	}

	public void setNascimento(LocalDate nascimento) {
		if (nascimento.isAfter(LocalDate.now()))
			throw new IllegalArgumentException("Data de nascimento inválida");
		this.nascimento = nascimento;
	}

	public void setTelefones(List<String> telefones) {
		this.telefones.addAll(telefones);
	}
}
