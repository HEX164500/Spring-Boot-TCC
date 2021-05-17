package com.sigen.api.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode( onlyExplicitlyIncluded = true )
@Table( name = "categorias" )
@Entity
public class Categoria {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@EqualsAndHashCode.Include
	@JsonProperty( access = Access.READ_ONLY )
	private Long id;

	@Column( nullable = false )
	private String nome;
	private String descricao = "";

	@JsonIgnore
	@ManyToMany( mappedBy = "categorias" )
	@LazyCollection( LazyCollectionOption.EXTRA )
	private final Set<Produto> produtos = new HashSet<>();
	
	public Categoria( Long id ) {
		this.id = id;
	}
	
}
