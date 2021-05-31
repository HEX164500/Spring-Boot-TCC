package com.sigen.api.controllers;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@PreAuthorize("permitAll()")
@RestController
public class DefaultControllers {

	@RequestMapping(value = "/")
	public ResponseEntity<String> home() {
		return ResponseEntity.ok("Welcome - " + LocalDate.now());
	}

	@RequestMapping(value = "/error")
	public ResponseEntity<String> error() {
		throw new RuntimeException("Unknow Error");
	}

	@RequestMapping(value = "/auth/identity")
	public ResponseEntity<String> authIdentity(Authentication authContext) {
		if (authContext instanceof AnonymousAuthenticationToken || authContext == null)
			return new ResponseEntity<String>("Não Autenticado", HttpStatus.FORBIDDEN);

		return ResponseEntity.ok("Autenticado com usuario : " + authContext.getName());
	}
}
