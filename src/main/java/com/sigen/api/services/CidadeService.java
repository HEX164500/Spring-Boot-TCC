package com.sigen.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sigen.api.dto.CidadeDTO;
import com.sigen.api.entities.Cidade;
import com.sigen.api.exceptions.NotFoundException;
import com.sigen.api.repositories.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repository;


	@Transactional
	public CidadeDTO save(Cidade cidade) {
		cidade.setId(null);
		return new CidadeDTO(repository.saveAndFlush(cidade));
	}


	@Transactional
	public CidadeDTO patch(Long id, Cidade cidade) {
		if (!repository.existsById(id))
			throw new NotFoundException("Cidade não encontrada");
		cidade.setId(id);
		return new CidadeDTO(repository.saveAndFlush(cidade));
	}


	@Transactional
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
}
