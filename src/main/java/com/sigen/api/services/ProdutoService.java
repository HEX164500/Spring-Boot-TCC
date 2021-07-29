package com.sigen.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sigen.api.dto.ProdutoDTO;
import com.sigen.api.entities.Categoria;
import com.sigen.api.entities.Produto;
import com.sigen.api.exceptions.NotFoundException;
import com.sigen.api.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;

	@Transactional(readOnly = true)
	public ProdutoDTO findById(Long id) {
		Produto produto = repository.findById(id).orElseThrow(() -> new NotFoundException("Produto não encontrado"));
		return new ProdutoDTO(produto);
	}

	@Transactional(readOnly = true)
	public Page<ProdutoDTO> findAllByValorGreaterThanEqualAndValorLessThanEqual(Double min, Double max, Pageable page) {
		return repository.findAllByValorGreaterThanEqualAndValorLessThanEqual(min, max, page)
				.map(produto -> new ProdutoDTO(produto));
	}

	@Transactional(readOnly = true)
	public Page<ProdutoDTO> findAllByNomeContainingOrDescricaoContaining(String text, Pageable page) {
		return repository.findAllByNomeContainingOrDescricaoContaining(text, text, page)
				.map(produto -> new ProdutoDTO(produto));
	}

	@Transactional(readOnly = true)
	public Page<ProdutoDTO> findAllByCategorias(Long id, Pageable page) {
		Categoria categoria = new Categoria();
		categoria.setId(id);
		return repository.findAllByCategorias(categoria, page).map(produto -> new ProdutoDTO(produto));
	}


	@Transactional
	public ProdutoDTO save(Produto produto) {
		Produto prod = repository.save(produto);
		return new ProdutoDTO(repository.findById(prod.getId()).orElse(null));
	}


	@Transactional
	public ProdutoDTO patch(Long id, Produto produto) {
		if (!repository.existsById(id))
			throw new NotFoundException("Produto não encontrado");
		produto.setId(id);
		Produto prod = repository.saveAndFlush(produto);
		return new ProdutoDTO(repository.findById(prod.getId()).orElse(null));
	}


	@Transactional
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
}
