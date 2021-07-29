package com.sigen.api.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.sigen.api.enums.NivelDeAcesso;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode( callSuper = true )
@Table(name = "funcionarios")
@Entity
@PrimaryKeyJoinColumn(name = "id_usuario")
public class Funcionario extends Usuario{

	@Column(nullable = false)
	private String funcao;
	
	@Column( nullable = false )
	private Double salario;
	
	@JsonProperty(access = Access.READ_ONLY)
	private LocalDate admissao = LocalDate.now();
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "id_departamento")
	private Departamento departamento;
	
	public Funcionario(Long id) {
		setId(id);
	}
	
	/**
	 * Configura dados padrão para um funcionario, como ter conta ativa e nivel de acesso
	 */
	public void configureDefaults() {
		setAcesso(NivelDeAcesso.EMPREGADO);
		setAtivo(true);
	}
	
	public void setSalario(Double salario) {
		if( salario < 0 )
			throw new IllegalArgumentException("Salario deve ser maior ou igual a 0");
		this.salario = salario;
	}
}