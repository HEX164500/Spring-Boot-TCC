package com.sigen.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sigen.api.dto.UsuarioDTO;
import com.sigen.api.entities.Usuario;
import com.sigen.api.enums.AccessLevel;
import com.sigen.api.exceptions.NotFoundException;
import com.sigen.api.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Transactional(readOnly = true)
	public UsuarioDTO findByIdOrCpf(Long id, String cpf) {
		Usuario usuario = repository.findByIdOrCpf(id, cpf)
				.orElseThrow(() -> new NotFoundException("Usuario não encontrado"));
		return new UsuarioDTO(usuario);
	}

	public UsuarioDTO save(Usuario usuario) {

		return new UsuarioDTO(repository.save(usuario));
	}

	public UsuarioDTO patch(Long id, Usuario usuario) {
		if (!repository.existsById(id))
			throw new NotFoundException("Usuario não encontrado");
		usuario.setId(id);
		return new UsuarioDTO(repository.saveAndFlush(usuario));
	}

	public void desativarConta(Long id) {
		if (!repository.existsById(id))
			new NotFoundException("Usuario não encontrado");
		repository.updateAtivoById(false, id);
	}

	public void ativarConta(Long id) {
		if (!repository.existsById(id))
			new NotFoundException("Usuario não encontrado");
		repository.updateAtivoById(true, id);
	}

	public void alterarNivelDeAcesso(Long id, AccessLevel level) {
		throw new Error("Não implementado");
		/*if (!repository.existsById(id))
			new NotFoundException("Usuario não encontrado");
		repository.updateAtivoById(true, id);
		*/
	}
}
