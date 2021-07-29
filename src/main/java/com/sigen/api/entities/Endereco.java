package com.sigen.api.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "enderecos")
@Entity
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(access = Access.READ_ONLY)
	@EqualsAndHashCode.Include
	private Long id;

	@Column(nullable = false, length = 20)
	private String apelido;

	@Column(nullable = false, length = 8, updatable = false)
	private String cep;

	@Column(nullable = false, length = 80)
	private String rua;

	@Column(nullable = false, length = 8)
	private String numero;

	private String complemento = "";

	@Column(nullable = false, length = 126, updatable = false)
	private String bairro;

	@ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_cidade", updatable = false)
	private Cidade cidade;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_usuario", updatable = false)
	@JsonProperty(access = Access.READ_ONLY)
	private Usuario usuario;

	public Endereco(Long id) {
		this.id = id;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public void setCep(String cep) {

		if (cep.length() != 8)
			throw new IllegalArgumentException("CEP inválido");
		try {
			Integer.parseInt(cep);
			this.cep = cep;
		} catch (Exception e) {
			throw new IllegalArgumentException("CEP inválido");
		}
	}
}
