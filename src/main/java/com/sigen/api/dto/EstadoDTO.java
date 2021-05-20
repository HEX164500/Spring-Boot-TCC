package com.sigen.api.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.sigen.api.entities.Estado;

import lombok.Getter;

@Getter
public class EstadoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String sigla;

	private final List<CidadeDTO> cidades = new ArrayList<>();

	public EstadoDTO(Estado estado) {
		id = estado.getId();
		nome = estado.getNome();
		sigla = estado.getSigla();
		cidades.addAll(estado.getCidades().stream().map(cidade -> new CidadeDTO(cidade)).collect(Collectors.toList()));
	}
}
