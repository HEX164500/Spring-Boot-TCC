package com.sigen.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sigen.api.dto.CidadeDTO;
import com.sigen.api.entities.Cidade;
import com.sigen.api.services.CidadeService;

@PreAuthorize("hasAuthority('EMPREGADO')")
@RestController
@RequestMapping(value = "/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeController {

	@Autowired
	private CidadeService service;

	@PostMapping
	public ResponseEntity<CidadeDTO> save(@RequestBody Cidade cidade) {
		return new ResponseEntity<CidadeDTO>(service.save(cidade), HttpStatus.CREATED);
	}

	@PatchMapping(value = "/{id}")
	public ResponseEntity<CidadeDTO> patch(@PathVariable Long id, @RequestBody Cidade cidade) {
		return ResponseEntity.ok(service.patch(id, cidade));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<CidadeDTO> deleteById(@PathVariable Long id) {
		service.deleteById(id);
		return new ResponseEntity<CidadeDTO>(HttpStatus.NO_CONTENT);
	}
}
