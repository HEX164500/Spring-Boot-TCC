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

import com.sigen.api.dto.CategoriaDTO;
import com.sigen.api.dto.ProdutoDTO;
import com.sigen.api.entities.Categoria;
import com.sigen.api.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoriaController {

	@Autowired
	private CategoriaService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<CategoriaDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@GetMapping
	public ResponseEntity<Page<CategoriaDTO>> findAll(Pageable page) {
		return ResponseEntity.ok(service.findAll(page));
	}

	@GetMapping( value = "/{id}/produtos" )
	public ResponseEntity<Page<ProdutoDTO>> findAllProducts(Long id, Pageable page) {
		return ResponseEntity.ok(service.findAllProducts(id, page));
	}
	
	@PostMapping
	public ResponseEntity<CategoriaDTO> save(@RequestBody Categoria categoria) {
		return new ResponseEntity<CategoriaDTO>(service.save(categoria), HttpStatus.CREATED);
	}

	@PatchMapping(value = "/{id}")
	public ResponseEntity<CategoriaDTO> patch(@PathVariable Long id, @RequestBody Categoria categoria) {
		return ResponseEntity.ok(service.patch(id, categoria));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<CategoriaDTO> deleteById(@PathVariable Long id) {
		service.deleteById(id);
		return new ResponseEntity<CategoriaDTO>(HttpStatus.NO_CONTENT);
	}
}
