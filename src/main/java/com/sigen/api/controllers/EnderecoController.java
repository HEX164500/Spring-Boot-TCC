package com.sigen.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.sigen.api.dto.EnderecoDTO;
import com.sigen.api.entities.Endereco;
import com.sigen.api.services.EnderecoService;

@RestController
@RequestMapping(value = "/enderecos", produces = MediaType.APPLICATION_JSON_VALUE)
public class EnderecoController {

	@Autowired
	private EnderecoService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<EnderecoDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@PostMapping
	public ResponseEntity<EnderecoDTO> save(@RequestBody Endereco endereco) {
		return new ResponseEntity<EnderecoDTO>(service.save(endereco), HttpStatus.CREATED);
	}

	@PatchMapping(value = "/{id}")
	public ResponseEntity<EnderecoDTO> patch(@PathVariable Long id, @RequestBody Endereco endereco) {
		return ResponseEntity.ok(service.patch(id, endereco));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<EnderecoDTO> deleteById(@PathVariable Long id) {
		service.deleteById(id);
		return new ResponseEntity<EnderecoDTO>(HttpStatus.NO_CONTENT);
	}
}
