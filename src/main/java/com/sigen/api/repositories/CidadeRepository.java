package com.sigen.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sigen.api.entities.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

}
