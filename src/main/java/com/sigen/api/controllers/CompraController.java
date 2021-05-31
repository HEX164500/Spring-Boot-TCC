package com.sigen.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sigen.api.dto.CompraDTO;
import com.sigen.api.entities.Compra;
import com.sigen.api.enums.EstadoPagamento;
import com.sigen.api.services.CompraService;

@PreAuthorize("permitAll()")
@RestController
@RequestMapping(value = "/compras", produces = MediaType.APPLICATION_JSON_VALUE)
public class CompraController {

	@Autowired
	private CompraService service;

	@PreAuthorize("hasAuthority('EMPREGADO')")
	@GetMapping(value = "/{id}")
	public ResponseEntity<CompraDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@PreAuthorize("hasAuthority('EMPREGADO')")
	@GetMapping(value = "/usuario/{id}")
	public ResponseEntity<Page<CompraDTO>> findAllByUsuario(@PathVariable Long id,
			@RequestParam(defaultValue = "PENDENTE") EstadoPagamento estado, Pageable page) {
		return ResponseEntity.ok(service.findAllByUsuarioAndEstado(id, estado, page));
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping
	public ResponseEntity<CompraDTO> save(@RequestBody Compra compra, Authentication userContext) {

		if (userContext instanceof AnonymousAuthenticationToken || userContext == null) {
			throw new AuthenticationCredentialsNotFoundException("Não foi possivel identificar o cliente");
		}

		String username = userContext.getName();

		return new ResponseEntity<CompraDTO>(service.save(compra, username), HttpStatus.CREATED);
	}

	@PreAuthorize("isAuthenticated()")
	@PatchMapping(value = "/cancelar/{id}")
	public ResponseEntity<CompraDTO> cancelar(@PathVariable Long id) {
		service.cancelar(id);
		return ResponseEntity.ok(null);
	}

	@PreAuthorize("hasAuthority('EMPREGADO')")
	@PatchMapping(value = "/completar/{id}")
	public ResponseEntity<CompraDTO> completar(@PathVariable Long id) {
		service.completar(id);
		return ResponseEntity.ok(null);
	}
}
