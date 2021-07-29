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

import com.sigen.api.dto.FuncionarioDTO;
import com.sigen.api.entities.Funcionario;
import com.sigen.api.services.FuncionarioService;

@PreAuthorize("hasAuthority('EMPREGADO')")
@RestController
@RequestMapping(value = "/funcionarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class FuncionarioController {

	@Autowired
	private FuncionarioService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<FuncionarioDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@GetMapping
	public ResponseEntity<Page<FuncionarioDTO>> findAll(Pageable page) {
		return ResponseEntity.ok(service.findAll(page));
	}

	@GetMapping(value = "/departamento/{id}")
	public ResponseEntity<Page<FuncionarioDTO>> findAllByDepartamento(@PathVariable Long id, Pageable page) {
		return ResponseEntity.ok(service.findAllByDepartamento(id, page));
	}

	@PostMapping
	public ResponseEntity<FuncionarioDTO> save(@RequestBody Funcionario funcionario) {
		
		
		
		return new ResponseEntity<FuncionarioDTO>(service.save(funcionario), HttpStatus.CREATED);
	}

	@PreAuthorize("hasAnyAuthority('EMPREGADO')")
	@PatchMapping(value = "/{id}")
	public ResponseEntity<FuncionarioDTO> patch(@PathVariable Long id, @RequestBody Funcionario funcionario) {
		return ResponseEntity.ok(service.patch(id, funcionario));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<FuncionarioDTO> deleteById(@PathVariable Long id) {
		service.deleteById(id);
		return new ResponseEntity<FuncionarioDTO>(HttpStatus.NO_CONTENT);
	}
}
