package com.sigen.api.dto;

import java.io.Serializable;

import com.sigen.api.entities.Funcionario;

import lombok.Getter;

@Getter
public class FuncionarioDTO extends UsuarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String funcao;
	private Double salario;
	private String admissao;

	private DepartamentoDTO departamento;

	public FuncionarioDTO(Funcionario funcionario) {
		super(funcionario);
		funcao = funcionario.getFuncao();
		salario = funcionario.getSalario();
		admissao = funcionario.getAdmissao().toString();
		departamento = new DepartamentoDTO(funcionario.getDepartamento());
	}
}
