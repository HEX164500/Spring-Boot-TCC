package com.sigen.api.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sigen.api.dto.UsuarioDTO;
import com.sigen.api.entities.Usuario;
import com.sigen.api.services.UsuarioService;

@PreAuthorize("permitAll()")
@RestController
@RequestMapping(value = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@PreAuthorize("hasAnyAuthority('EMPREGADO', #id, #cpf)")
	@GetMapping(value = "/search")
	public ResponseEntity<UsuarioDTO> findByIdOrCpf(@RequestParam(defaultValue = "-1", name = "id") Long id,
			@RequestParam(defaultValue = "-1", name = "cpf") String cpf) {

		if (cpf.equals("-1") && id == -1)
			throw new IllegalArgumentException("Deve ser fornecido ao menos um parametro para busca");

		if (!cpf.equals("-1") && id != -1) {
			throw new IllegalArgumentException("Deve ser fornecido somente um parametro para busca");
		}

		return ResponseEntity.ok(service.findByIdOrCpf(id, cpf));
	}

	@GetMapping(value = "/ativar/token/{token}", produces = MediaType.TEXT_HTML_VALUE)
	public ModelAndView ativarContaPorToken(@PathVariable String token) {

		ModelAndView view = new ModelAndView(
				service.ativarContaPorToken(token) ? "mensagem_conta_ativada" : "mensagem_conta_nao_ativada");
		return view;
	}

	@PostMapping
	public ResponseEntity<UsuarioDTO> save(@RequestBody Usuario usuario) {
		return new ResponseEntity<UsuarioDTO>(service.save(usuario), HttpStatus.CREATED);
	}

	@PreAuthorize("hasAnyAuthority('EMPREGADO', #id)")
	@PatchMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO> patch(@PathVariable("id") Long id, @RequestBody Usuario usuario) {
		return ResponseEntity.ok(service.patch(id, usuario));
	}

	@PatchMapping(value = "/alterarsenha/{id}")
	public ResponseEntity<UsuarioDTO> changePassword(@PathVariable("id") Long id, @RequestBody Map<String, String> corpo){
		
		String nova = corpo.get("nova");
		String antiga = corpo.get("antiga");

		service.alterarSenha(id, nova, antiga);

		return new ResponseEntity<UsuarioDTO>(HttpStatus.NO_CONTENT);
	}
}
