package com.sigen.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sigen.api.dto.DepartamentoDTO;
import com.sigen.api.entities.Departamento;
import com.sigen.api.exceptions.NotFoundException;
import com.sigen.api.repositories.DepartamentoRepository;

@Service
public class DepartamentoService {

	@Autowired
	private DepartamentoRepository repository;

	@Transactional(readOnly = true)
	public DepartamentoDTO findById(Long id) {
		Departamento departamento = repository.findById(id)
				.orElseThrow(() -> new NotFoundException("Departamento não encontrado"));
		return new DepartamentoDTO(departamento);
	}

	@Transactional(readOnly = true)
	public Page<DepartamentoDTO> findAll(Pageable page) {
		return repository.findAll(page).map(departamento -> new DepartamentoDTO(departamento));
	}

	public DepartamentoDTO save(Departamento departamento) {
		return new DepartamentoDTO(repository.save(departamento));
	}

	public DepartamentoDTO patch(Long id, Departamento departamento) {
		if (!repository.existsById(id))
			throw new NotFoundException("Departamento não encontrado");
		departamento.setId(id);
		return new DepartamentoDTO(repository.saveAndFlush(departamento));
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}
}
