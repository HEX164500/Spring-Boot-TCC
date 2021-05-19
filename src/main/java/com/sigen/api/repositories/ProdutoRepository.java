package com.sigen.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sigen.api.entities.Categoria;
import com.sigen.api.entities.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	Page<Produto> findAllByNomeContainingOrDescricaoContaining(String text, String text2, Pageable pageable);

	Page<Produto> findAllByValorGreaterThanEqualAndValorLessThanEqual(Double min, Double max, Pageable pageable);
	
	Page<Produto> findAllByCategorias(Categoria categoria, Pageable pageable);
}
