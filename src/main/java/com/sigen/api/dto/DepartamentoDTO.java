package com.sigen.api.dto;

import java.io.Serializable;

import com.sigen.api.entities.Departamento;

import lombok.Getter;

@Getter
public class DepartamentoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String descricao;

	public DepartamentoDTO(Departamento departamento) {
		id = departamento.getId();
		nome = departamento.getNome();
		descricao = departamento.getDescricao();
	}
}
