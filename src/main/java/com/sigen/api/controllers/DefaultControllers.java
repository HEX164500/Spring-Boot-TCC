package com.sigen.api.controllers;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sigen.api.dto.IdentityControllerDTO;
import com.sigen.api.enums.NivelDeAcesso;

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
	public ResponseEntity<IdentityControllerDTO> authIdentity(Authentication authContext) {

		IdentityControllerDTO idCtl = new IdentityControllerDTO();

		idCtl.setUsername("Não autenticado");

		if (authContext instanceof AnonymousAuthenticationToken || authContext == null)
			return new ResponseEntity<IdentityControllerDTO>(idCtl, HttpStatus.FORBIDDEN);

		idCtl.setUsername(authContext.getName());

		var permission = authContext.getAuthorities().stream().filter(a -> {
			return a.getAuthority().equals("EMPREGADO") || a.getAuthority().equals("USUARIO");
		}).findFirst();

		try {
			if (permission != null) {
				idCtl.setAcesso(NivelDeAcesso.valueOf(permission.get().getAuthority()));
			} else {
				idCtl.setAcesso(NivelDeAcesso.USUARIO);
			}
		} catch (Exception e) {
			idCtl.setAcesso(NivelDeAcesso.USUARIO);
		}

		return ResponseEntity.ok(idCtl);
	}
}
