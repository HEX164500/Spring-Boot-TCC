package com.sigen.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sigen.api.dto.EstadoDTO;
import com.sigen.api.entities.Estado;
import com.sigen.api.exceptions.NotFoundException;
import com.sigen.api.repositories.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repository;

	@Transactional(readOnly = true)
	public Page<EstadoDTO> findAll(Pageable page) {
		return repository.findAll(page).map(estado -> new EstadoDTO(estado));
	}


	@Transactional
	public EstadoDTO save(Estado estado) {
		return new EstadoDTO(repository.save(estado));
	}


	@Transactional
	public EstadoDTO patch(Long id, Estado estado) {
		if (!repository.existsById(id))
			throw new NotFoundException("Estado não encontrado");
		estado.setId(id);
		return new EstadoDTO(repository.saveAndFlush(estado));
	}


	@Transactional
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
}
