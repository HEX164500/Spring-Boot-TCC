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
import org.springframework.web.bind.annotation.RestController;

import com.sigen.api.dto.EstadoDTO;
import com.sigen.api.entities.Estado;
import com.sigen.api.services.EstadoService;


@PreAuthorize("hasAuthority('EMPREGADO')")
@RestController
@RequestMapping(value = "/estados", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstadoController {

	@Autowired
	private EstadoService service;

	@PreAuthorize("permitAll()")
	@GetMapping
	public ResponseEntity<Page<EstadoDTO>> findAll(Pageable page) {
		return ResponseEntity.ok(service.findAll(page));
	}
	
	@PostMapping
	public ResponseEntity<EstadoDTO> save(@RequestBody Estado estado) {
		return new ResponseEntity<EstadoDTO>(service.save(estado), HttpStatus.CREATED);
	}

	@PatchMapping(value = "/{id}")
	public ResponseEntity<EstadoDTO> patch(@PathVariable Long id, @RequestBody Estado estado) {
		return ResponseEntity.ok(service.patch(id, estado));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<EstadoDTO> deleteById(@PathVariable Long id) {
		service.deleteById(id);
		return new ResponseEntity<EstadoDTO>(HttpStatus.NO_CONTENT);
	}
}
