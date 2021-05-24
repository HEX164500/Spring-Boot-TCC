package com.sigen.api.dto;

import java.io.Serializable;

import com.sigen.api.entities.ItemCompra;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ItemCompraDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	private Long id;
	private ProdutoDTO produto;
	private Integer quantidade;

	public ItemCompraDTO(ItemCompra item) {
		id = item.getId();
		produto = new ProdutoDTO(item.getProduto());
		quantidade = item.getQuantidade();
	}

}
