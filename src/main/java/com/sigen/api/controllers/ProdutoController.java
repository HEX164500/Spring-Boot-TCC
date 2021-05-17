package com.sigen.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sigen.api.dto.ProdutoDTO;
import com.sigen.api.entities.Produto;
import com.sigen.api.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProdutoController {

	@Autowired
	private ProdutoService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<ProdutoDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@GetMapping
	public ResponseEntity<Page<ProdutoDTO>> findAll(Pageable page) {
		return ResponseEntity.ok(service.findAll(page));
	}

	@PostMapping
	public ResponseEntity<ProdutoDTO> save(@RequestBody Produto produto) {
		return new ResponseEntity<ProdutoDTO>(service.save(produto), HttpStatus.CREATED);
	}

	@PatchMapping(value = "/{id}")
	public ResponseEntity<ProdutoDTO> patch(@PathVariable Long id, @RequestBody Produto produto) {
		return ResponseEntity.ok(service.patch(id, produto));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ProdutoDTO> deleteById(@PathVariable Long id) {
		service.deleteById(id);
		return new ResponseEntity<ProdutoDTO>(HttpStatus.NO_CONTENT);
	}
}
