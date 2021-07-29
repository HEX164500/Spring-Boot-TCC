package com.sigen.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sigen.api.dto.EnderecoDTO;
import com.sigen.api.entities.Endereco;
import com.sigen.api.entities.Usuario;
import com.sigen.api.exceptions.NotFoundException;
import com.sigen.api.repositories.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repository;

	@Transactional(readOnly = true)
	public Page<EnderecoDTO> findAllByUsuario(Long id, Pageable page) {
		return repository.findAllByUsuario(new Usuario(id), page).map(endereco -> new EnderecoDTO(endereco));
	}


	@Transactional
	public EnderecoDTO save(Endereco endereco, Long idUsuario) {
		endereco.setUsuario(new Usuario(idUsuario));

		Endereco enderecoSaved = repository.save(endereco);
		return new EnderecoDTO(repository.findById(enderecoSaved.getId()).orElse(null));
	}


	@Transactional
	public EnderecoDTO patch(Long id, Endereco endereco, Long idUsuario) {

		Endereco original = repository.findById(id).orElseThrow(() -> new NotFoundException("Endereco não encontrado"));

		if (original.getUsuario().getId() != idUsuario)
			throw new AccessDeniedException("Não autorizado");

		endereco.setId(id);
		Endereco enderecoSaved = repository.saveAndFlush(endereco);
		return new EnderecoDTO(repository.findById(enderecoSaved.getId()).orElse(enderecoSaved));
	}


	@Transactional
	public void deleteById(Long id, Long idUsuario) {

		Usuario usuario = new Usuario(idUsuario);

		Endereco original = repository.findById(id).orElseThrow(() -> new NotFoundException("Endereco não encontrado"));

		if (original.getUsuario().getId() != usuario.getId())
			throw new AccessDeniedException("Não autorizado");

		repository.deleteById(id);
	}
}
