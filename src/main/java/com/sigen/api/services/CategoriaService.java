package com.sigen.api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sigen.api.dto.CategoriaDTO;
import com.sigen.api.dto.ProdutoDTO;
import com.sigen.api.entities.Categoria;
import com.sigen.api.exceptions.NotFoundException;
import com.sigen.api.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	@Transactional(readOnly = true)
	public CategoriaDTO findById(Long id) {
		Categoria categoria = repository.findById(id)
				.orElseThrow(() -> new NotFoundException("Categoria não encontrada"));
		return new CategoriaDTO(categoria);
	}

	@Transactional(readOnly = true)
	public Page<CategoriaDTO> findAll(Pageable page) {
		return repository.findAll(page).map(categoria -> new CategoriaDTO(categoria));
	}

	@Transactional(readOnly = true)
	public Page<ProdutoDTO> findAllProducts(Long id, Pageable page) {
		Categoria categoria = repository.findById(id)
				.orElseThrow(() -> new NotFoundException("Categoria não encontrada"));

		List<ProdutoDTO> produtos = categoria.getProdutos().stream().map(produto -> new ProdutoDTO(produto))
				.collect(Collectors.toList());

		return new PageImpl<ProdutoDTO>(produtos, page, produtos.size());
	}

	public CategoriaDTO save(Categoria categoria) {
		return new CategoriaDTO(repository.save(categoria));
	}

	public CategoriaDTO patch(Long id, Categoria categoria) {
		if (!repository.existsById(id))
			throw new NotFoundException("Categoria não encontrada");
		categoria.setId(id);
		return new CategoriaDTO(repository.save(categoria));
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}
}
