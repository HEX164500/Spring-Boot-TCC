package com.sigen.api.entities;

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
@Table(name = "cidades")
@Entity
public class Cidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(access = Access.READ_ONLY)
	@EqualsAndHashCode.Include
	private Long id;

	@Column(nullable = false, length = 80)
	private String nome;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_estado")
	private Estado estado;

	@JsonProperty("estado")
	public void setEstado(Long id) {
		estado = new Estado(id);
	}

	public Cidade(Long id) {
		this.id = id;
	}
}
