package com.sigen.api.dto;

import java.io.Serializable;

import com.sigen.api.entities.Endereco;

import lombok.Getter;

@Getter
public class EnderecoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String apelido;
	private String cep;
	private String rua;
	private String numero;
	private String complemento;
	private String bairro;
	private CidadeDTO cidade;
	private UsuarioDTO usuario;

	public EnderecoDTO(Endereco endereco) {
		id = endereco.getId();
		apelido = endereco.getApelido();
		cep = endereco.getCep();
		rua = endereco.getRua();
		numero = endereco.getNumero();
		complemento = endereco.getComplemento();
		bairro = endereco.getBairro();
		cidade = new CidadeDTO(endereco.getCidade());
		usuario = new UsuarioDTO(endereco.getUsuario());
	}
}
