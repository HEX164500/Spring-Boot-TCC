package com.sigen.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sigen.api.entities.Compra;
import com.sigen.api.entities.Usuario;
import com.sigen.api.enums.EstadoPagamento;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {

	Page<Compra> findAllByUsuarioAndEstado(Usuario usuario, EstadoPagamento estado, Pageable pageable);
}
