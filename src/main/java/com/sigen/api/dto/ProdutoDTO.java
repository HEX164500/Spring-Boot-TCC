package com.sigen.api.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.sigen.api.entities.Produto;

import lombok.Getter;

@Getter
public class ProdutoDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String descricao;
	private Double valor;
	private String cadastro;
	private Double peso;
	private Integer estoque;
	private String cor;
	private String tamanho;
	private String banner;
	private Set<String> imagens;
	private Set<CategoriaDTO> categorias = new HashSet<>();

	public ProdutoDTO(Produto produto) {
		id = produto.getId();
		nome = produto.getNome();
		descricao = produto.getDescricao();
		valor = produto.getValor();
		cadastro = produto.getCadastro().toString();
		peso = produto.getPeso();
		estoque = produto.getEstoque();
		cor = produto.getCor();
		tamanho = produto.getTamanho();
		banner = produto.getBanner();
		imagens = produto.getImagens();

		categorias.addAll(produto.getCategorias().stream()
				.map(categoria -> new CategoriaDTO(categoria)).collect(Collectors.toList()));
	}
}
