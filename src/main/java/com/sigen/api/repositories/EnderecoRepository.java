package com.sigen.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sigen.api.entities.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}
