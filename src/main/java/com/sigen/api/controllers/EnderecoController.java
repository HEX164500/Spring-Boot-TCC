package com.sigen.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sigen.api.authentication.UserDetailsImpl;
import com.sigen.api.dto.EnderecoDTO;
import com.sigen.api.entities.Endereco;
import com.sigen.api.services.EnderecoService;

@PreAuthorize("hasAuthority('USUARIO')")
@RestController
@RequestMapping(value = "/enderecos", produces = MediaType.APPLICATION_JSON_VALUE)
public class EnderecoController {

	@Autowired
	private EnderecoService service;

	@GetMapping(value = "/usuario")
	public ResponseEntity<Page<EnderecoDTO>> findAllByUsuario(Pageable page, Authentication userContext) {
		Long userId = getUserIdFromAuthentication(userContext);

		return ResponseEntity.ok(service.findAllByUsuario(userId, page));
	}

	@PostMapping
	public ResponseEntity<EnderecoDTO> save(@RequestBody Endereco endereco, Authentication userContext) {

		Long userId = getUserIdFromAuthentication(userContext);

		return new ResponseEntity<EnderecoDTO>(service.save(endereco, userId), HttpStatus.CREATED);
	}

	@PatchMapping(value = "/{id}")
	public ResponseEntity<EnderecoDTO> patch(@PathVariable Long id, @RequestBody Endereco endereco,
			Authentication userContext) {

		Long userId = getUserIdFromAuthentication(userContext);
		return ResponseEntity.ok(service.patch(id, endereco, userId));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<EnderecoDTO> deleteById(@PathVariable Long id, Authentication userContext) {

		Long userId = getUserIdFromAuthentication(userContext);
		
		service.deleteById(id, userId);
		return new ResponseEntity<EnderecoDTO>(HttpStatus.NO_CONTENT);
	}

	private Long getUserIdFromAuthentication(Authentication userContext) {
		if (!(userContext instanceof UsernamePasswordAuthenticationToken) || userContext == null)
			throw new AuthenticationCredentialsNotFoundException("Não foi possivel identificar o cliente");

		UserDetails details = (UserDetails) userContext.getDetails();

		if (details == null || !(details instanceof UserDetailsImpl))
			throw new AuthenticationCredentialsNotFoundException("Não foi possivel identificar o cliente");

		return ((UserDetailsImpl) details).getUserId();
	}
}
