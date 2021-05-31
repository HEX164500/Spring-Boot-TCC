package com.sigen.api.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sigen.api.entities.Departamento;

import lombok.Getter;

@Getter
public class DepartamentoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String descricao;

	@JsonBackReference
	private FuncionarioDTO gerente;

	public DepartamentoDTO(Departamento departamento) {
		id = departamento.getId();
		nome = departamento.getNome();
		descricao = departamento.getDescricao();
		if( departamento.getGerente() != null ) {
			gerente = new FuncionarioDTO(departamento.getGerente());
		}
	}
}
