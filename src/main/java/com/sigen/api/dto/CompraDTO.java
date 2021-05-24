package com.sigen.api.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.sigen.api.entities.Compra;
import com.sigen.api.enums.EstadoPagamento;
import com.sigen.api.enums.MetodoPagamento;

import lombok.Getter;

@Getter
public class CompraDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private UsuarioDTO cliente;
	private LocalDate dataCompra;
	private Double total;
	private EstadoPagamento estado;
	private MetodoPagamento metodo;
	private LocalDate dataPagamento;
	private EnderecoDTO endereco;
	private Set<ItemCompraDTO> items;

	public CompraDTO(Compra compra) {
		id = compra.getId();
		cliente = new UsuarioDTO(compra.getUsuario());
		dataCompra = compra.getDataCompra();
		total = compra.getTotal();
		estado = compra.getEstado();
		metodo = compra.getMetodo();
		dataPagamento = compra.getDataPagamento();
		endereco = new EnderecoDTO(compra.getEndereco());
		items = new HashSet<>(
				compra.getItems().stream().map(item -> new ItemCompraDTO(item)).collect(Collectors.toList()));
	}
}
