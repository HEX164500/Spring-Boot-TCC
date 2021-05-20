package com.sigen.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sigen.api.dto.EnderecoDTO;
import com.sigen.api.entities.Endereco;
import com.sigen.api.exceptions.NotFoundException;
import com.sigen.api.repositories.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repository;

	@Transactional(readOnly = true)
	public EnderecoDTO findById(Long id) {
		Endereco endereco = repository.findById(id).orElseThrow(() -> new NotFoundException("Endereço não encontrado"));
		return new EnderecoDTO(endereco);
	}

	@Transactional(readOnly = true)
	public Page<EnderecoDTO> findAll(Pageable page) {
		return repository.findAll(page).map(endereco -> new EnderecoDTO(endereco));
	}

	public EnderecoDTO save(Endereco endereco) {
		Endereco enderecoSaved = repository.save(endereco);
		return new EnderecoDTO(repository.findById(enderecoSaved.getId()).orElse(null));
	}

	public EnderecoDTO patch(Long id, Endereco endereco) {
		if (!repository.existsById(id))
			throw new NotFoundException("Endereco não encontrado");
		endereco.setId(id);
		Endereco enderecoSaved = repository.saveAndFlush(endereco);
		return new EnderecoDTO(repository.findById(enderecoSaved.getId()).orElse(enderecoSaved));
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}
}
