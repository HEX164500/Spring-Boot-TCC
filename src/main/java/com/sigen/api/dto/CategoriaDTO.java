package com.sigen.api.dto;

import java.io.Serializable;

import com.sigen.api.entities.Categoria;

import lombok.Getter;

@Getter
public class CategoriaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String descricao;

	public CategoriaDTO(Categoria categoria) {
		id = categoria.getId();
		nome = categoria.getNome();
		descricao = categoria.getDescricao();
	}
}
