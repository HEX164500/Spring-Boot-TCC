package com.sigen.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sigen.api.entities.Endereco;
import com.sigen.api.entities.Usuario;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	Page<Endereco> findAllByUsuario(Usuario usuario, Pageable page);
}
