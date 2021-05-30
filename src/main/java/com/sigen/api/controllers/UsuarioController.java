package com.sigen.api.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sigen.api.dto.UsuarioDTO;
import com.sigen.api.entities.Usuario;
import com.sigen.api.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@GetMapping(value = "/search")
	public ResponseEntity<UsuarioDTO> findByIdOrCpf(@RequestParam(defaultValue = "-1") Long id,
			@RequestParam(defaultValue = "-1") String cpf) {

		System.out.println(id);
		System.out.println(cpf);
		
		if (cpf.equals("-1") && id == -1)
			throw new IllegalArgumentException("Deve ser fornecido ao menos um parametro para busca");

		if (!cpf.equals("-1") && id != -1) {
			throw new IllegalArgumentException("Deve ser fornecido somente um parametro para busca");
		}

		return ResponseEntity.ok(service.findByIdOrCpf(id, cpf));
	}

	@GetMapping(value = "/ativar/token/{token}")
	public ResponseEntity<UsuarioDTO> ativarContaPorToken(@PathVariable String token) {
		service.ativarContaPorToken(token);
		return new ResponseEntity<UsuarioDTO>(HttpStatus.NO_CONTENT);
	}

	@PostMapping
	public ResponseEntity<UsuarioDTO> save(@RequestBody Usuario usuario) {
		return new ResponseEntity<UsuarioDTO>(service.save(usuario), HttpStatus.CREATED);
	}

	@PatchMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO> patch(@PathVariable Long id, @RequestBody Usuario usuario) {
		return ResponseEntity.ok(service.patch(id, usuario));
	}

	@PatchMapping(value = "/alterarsenha/{id}")
	public ResponseEntity<UsuarioDTO> patch(@PathVariable Long id, @RequestBody Map<String, String> corpo) {

		String nova = corpo.get("nova");
		String antiga = corpo.get("antiga");

		service.alterarSenha(id, nova, antiga);

		return new ResponseEntity<UsuarioDTO>(HttpStatus.NO_CONTENT);
	}
}
