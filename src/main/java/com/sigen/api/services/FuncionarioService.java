package com.sigen.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sigen.api.dto.FuncionarioDTO;
import com.sigen.api.entities.Departamento;
import com.sigen.api.entities.Funcionario;
import com.sigen.api.exceptions.NotFoundException;
import com.sigen.api.repositories.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository repository;

	@Transactional(readOnly = true)
	public FuncionarioDTO findById(Long id) {
		Funcionario funcionario = repository.findById(id)
				.orElseThrow(() -> new NotFoundException("Funcionario não encontrado"));
		return new FuncionarioDTO(funcionario);
	}

	@Transactional(readOnly = true)
	public Page<FuncionarioDTO> findAllByDepartamento(Long id, Pageable page) {
		Departamento departamento = new Departamento(id);
		return repository.findAllByDepartamento(departamento, page).map(funcionario -> new FuncionarioDTO(funcionario));
	}

	@Transactional(readOnly = true)
	public Page<FuncionarioDTO> findAll(Pageable page) {
		return repository.findAll(page).map(funcionario -> new FuncionarioDTO(funcionario));
	}

	public FuncionarioDTO save(Funcionario funcionario) {
		funcionario.configureDefaults();
		Funcionario f = repository.findById(repository.save(funcionario).getId()).orElse(null);
		return new FuncionarioDTO(f);
	}

	public FuncionarioDTO patch(Long id, Funcionario funcionario) {
		if (!repository.existsById(id))
			throw new NotFoundException("Funcionario não encontrado");
		funcionario.setId(id);
		return new FuncionarioDTO(repository.saveAndFlush(funcionario));
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}
}
