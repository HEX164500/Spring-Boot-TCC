package com.sigen.api.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sigen.api.entities.Funcionario;

import lombok.Getter;

@Getter
public class FuncionarioDTO extends UsuarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String funcao;
	private Double salario;
	private LocalDate admissao;

	@JsonManagedReference
	private DepartamentoDTO departamento;

	public FuncionarioDTO(Funcionario funcionario) {
		super(funcionario);
		funcao = funcionario.getFuncao();
		salario = funcionario.getSalario();
		admissao = funcionario.getAdmissao();
		departamento = new DepartamentoDTO(funcionario.getDepartamento());
	}
}
