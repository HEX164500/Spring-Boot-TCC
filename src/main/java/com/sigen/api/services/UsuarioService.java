package com.sigen.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sigen.api.dto.UsuarioDTO;
import com.sigen.api.entities.Usuario;
import com.sigen.api.exceptions.NotFoundException;
import com.sigen.api.repositories.UsuarioRepository;
import com.sigen.api.services.mail.MailService;
import com.sigen.api.services.token.TokenBuilder;
import com.sigen.api.services.token.TokenUsuario;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private MailService mailService;

	@Transactional(readOnly = true)
	public UsuarioDTO findByIdOrCpf(Long id, String cpf) {
		Usuario usuario = repository.findByIdOrCpf(id, cpf)
				.orElseThrow(() -> new NotFoundException("Usuario não encontrado"));
		return new UsuarioDTO(usuario);
	}

	public UsuarioDTO save(Usuario usuario) {

		Usuario usr = repository.save(usuario);

		mailService.sendActivationToken(usr);

		return new UsuarioDTO(usr);
	}

	public UsuarioDTO patch(Long id, Usuario usuario) {
		if (!repository.existsById(id))
			throw new NotFoundException("Usuario não encontrado");
		usuario.setId(id);
		return new UsuarioDTO(repository.saveAndFlush(usuario));
	}

	public void ativarContaPorToken(String data) {
		TokenUsuario token = TokenBuilder.decode(data);
		Usuario usuario = repository.findByIdOrCpf(token.getId(), "")
				.orElseThrow(() -> new NotFoundException("Usuario não encontrado"));
		usuario.setAtivo(true);
		repository.save(usuario);
	}

	public void alterarSenha(Long id, String nova, String antiga) {

		if (nova == null || antiga == null)
			throw new IllegalArgumentException("Senhas não podem ser vazias");

		if (nova.equals(antiga))
			throw new IllegalArgumentException("Senhas não podem ser iguais");

		Usuario usuario = repository.findById(id).orElseThrow(() -> new NotFoundException("Usuario não encontrado"));
		
		if (!usuario.getSenha().equals(antiga))
			throw new IllegalArgumentException("Senha atual invalida");

		usuario.setSenha(nova);
		
		repository.updateSenhaById(id, usuario.getSenha());
		repository.save(usuario);
	}
}
