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


	@Transactional
	public UsuarioDTO save(Usuario usuario) {

		usuario.setId(null);
		Usuario usr = repository.save(usuario);

		mailService.sendActivationToken(usr);

		return new UsuarioDTO(usr);
	}


	@Transactional
	public UsuarioDTO patch(Long id, Usuario usuario) {
		if (!repository.existsById(id))
			throw new NotFoundException("Usuario não encontrado");
		usuario.setId(id);
		return new UsuarioDTO(repository.saveAndFlush(usuario));
	}


	@Transactional
	public boolean ativarContaPorToken(String data) {
		TokenUsuario token = TokenBuilder.decode(data);

		Usuario usuario = repository.findById(token.getId()).orElse(null);
		
		if( usuario == null )
			return false;
		
		if( !usuario.getEmail().equals(token.getEmail()) )
			return false;
		
		repository.updateAtivoById(true, token.getId());
		return true;
	}


	@Transactional
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
