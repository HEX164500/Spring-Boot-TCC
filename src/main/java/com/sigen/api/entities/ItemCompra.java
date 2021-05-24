package com.sigen.api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "item_compras")
public class ItemCompra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "id_compra", updatable = false)
	@JsonProperty( access = Access.READ_ONLY )
	private Compra compra;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "id_produto", updatable = false)
	private Produto produto;
	
	@Column(nullable = false, updatable = false)
	private Integer quantidade;
	
	public void setQuantidade(Integer quantidade) {
		if ( quantidade < 1)
			throw new IllegalArgumentException("Quantidade deve ser maior ou igual que 1");
		this.quantidade = quantidade;
	}
}
