package com.sigen.api.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "estados")
@Entity
public class Estado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(access = Access.READ_ONLY)
	@EqualsAndHashCode.Include
	private Long id;

	@Column(nullable = false, length = 50)
	private String nome;

	@Column(nullable = false, length = 5, unique = true)
	private String sigla;

	@JsonIgnore
	@OneToMany(mappedBy = "estado", fetch = FetchType.EAGER)
	private final Set<Cidade> cidades = new HashSet<>();

	public Estado(Long id) {
		this.id = id;
	}
}
