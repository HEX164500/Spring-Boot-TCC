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

import com.sigen.api.dto.DepartamentoDTO;
import com.sigen.api.entities.Departamento;
import com.sigen.api.services.DepartamentoService;

@RestController
@RequestMapping(value = "/departamentos", produces = MediaType.APPLICATION_JSON_VALUE)
public class DepartamentoController {

	@Autowired
	private DepartamentoService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<DepartamentoDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@GetMapping
	public ResponseEntity<Page<DepartamentoDTO>> findAll(Pageable page) {
		return ResponseEntity.ok(service.findAll(page));
	}
	
	@PostMapping
	public ResponseEntity<DepartamentoDTO> save(@RequestBody Departamento departamento) {
		return new ResponseEntity<DepartamentoDTO>(service.save(departamento), HttpStatus.CREATED);
	}

	@PatchMapping(value = "/{id}")
	public ResponseEntity<DepartamentoDTO> patch(@PathVariable Long id, @RequestBody Departamento departamento) {
		return ResponseEntity.ok(service.patch(id, departamento));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<DepartamentoDTO> deleteById(@PathVariable Long id) {
		service.deleteById(id);
		return new ResponseEntity<DepartamentoDTO>(HttpStatus.NO_CONTENT);
	}
}
