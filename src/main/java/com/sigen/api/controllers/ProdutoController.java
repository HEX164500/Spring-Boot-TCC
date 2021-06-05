package com.sigen.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sigen.api.dto.ProdutoDTO;
import com.sigen.api.entities.Produto;
import com.sigen.api.services.ProdutoService;

@PreAuthorize("permitAll()")
@RestController
@RequestMapping(value = "/produtos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProdutoController {

	private static final String LONG_MAX = "9223372036854775807";

	@Autowired
	private ProdutoService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<ProdutoDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@GetMapping
	public ResponseEntity<Page<ProdutoDTO>> findAllByValorGreaterThanEqualAndValorLessThanEqual(
			@RequestParam(defaultValue = "0") Double min, @RequestParam(defaultValue = LONG_MAX) Double max,
			Pageable page) {

		return ResponseEntity.ok(service.findAllByValorGreaterThanEqualAndValorLessThanEqual(min, max, page));
	}

	@GetMapping(value = "/search/{text}")
	public ResponseEntity<Page<ProdutoDTO>> findAllByNomeLikeOrDescricaoLike(@PathVariable String text, Pageable page) {

		return ResponseEntity.ok(service.findAllByNomeContainingOrDescricaoContaining(text, page));
	}

	@GetMapping(value = "/categoria/{id}")
	public ResponseEntity<Page<ProdutoDTO>> findAllByCategorias(@PathVariable Long id, Pageable page) {
		return ResponseEntity.ok(service.findAllByCategorias(id, page));
	}

	@PreAuthorize("hasAuthority('EMPREGADO')")
	@PostMapping
	public ResponseEntity<ProdutoDTO> save(@RequestBody Produto produto) {
		return new ResponseEntity<ProdutoDTO>(service.save(produto), HttpStatus.CREATED);
	}

	@PreAuthorize("hasAuthority('EMPREGADO')")
	@PatchMapping(value = "/{id}")
	public ResponseEntity<ProdutoDTO> patch(@PathVariable Long id, @RequestBody Produto produto) {
		return ResponseEntity.ok(service.patch(id, produto));
	}

	@PreAuthorize("hasAuthority('EMPREGADO')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ProdutoDTO> deleteById(@PathVariable Long id) {
		service.deleteById(id);
		return new ResponseEntity<ProdutoDTO>(HttpStatus.NO_CONTENT);
	}
}
