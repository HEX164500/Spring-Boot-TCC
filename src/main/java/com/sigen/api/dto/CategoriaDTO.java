package com.sigen.api.dto;

import com.sigen.api.entities.Categoria;

import lombok.Getter;

@Getter
public class CategoriaDTO {

	private Long id;
	private String nome;
	private String descricao;

	public CategoriaDTO(Categoria categoria) {
		id = categoria.getId();
		nome = categoria.getNome();
		descricao = categoria.getDescricao();
	}
}
