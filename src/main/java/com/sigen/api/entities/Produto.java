package com.sigen.api.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "produtos")
@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;

	@Column(nullable = false)
	private String nome;

	private String descricao = "";

	@Column(nullable = false)
	private Double valor;

	@JsonProperty(access = Access.READ_ONLY)
	@Column(updatable = false)
	private LocalDate cadastro = LocalDate.now();

	private Double peso = 0.0;

	private Integer estoque = 0;

	private String cor = "";

	private String tamanho = "";

	@Column(nullable = false)
	private String banner = "";

	@ElementCollection(fetch = FetchType.EAGER)
	private final Set<String> imagens = new HashSet<>();


	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "produto_categoria",
			joinColumns = @JoinColumn(name = "id_produto"),
			inverseJoinColumns = @JoinColumn(name = "id_categoria"))
	private final Set<Categoria> categorias = new HashSet<>();

	public Produto(Long id) {
		this.id = id;
	}
	
	public void setCategorias(List<Categoria> categorias) {
		this.categorias.addAll(categorias);
	}

	public void setImagens(List<String> imagens) {
		this.imagens.addAll(imagens);
	}

	public void setValor(Double valor) {
		if (valor < 0)
			throw new IllegalArgumentException("Valor invalido");
		this.valor = valor;
	}

	public void setPeso(Double peso) {
		if (peso < 0)
			throw new IllegalArgumentException("Peso invalido");
		this.peso = peso;
	}

	public void setEstoque(Integer estoque) {
		if (estoque < 0)
			throw new IllegalArgumentException("Estoque invalido");
		this.estoque = estoque;
	}
}
