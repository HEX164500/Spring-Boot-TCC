package com.sigen.api.dto;

import java.io.Serializable;

import com.sigen.api.entities.Cidade;

import lombok.Getter;

@Getter
public class CidadeDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private Long estado;

	public CidadeDTO(Cidade cidade) {
		id = cidade.getId();
		nome = cidade.getNome();
		estado = cidade.getEstado().getId();
	}
}
