package com.sigen.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sigen.api.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Transactional(readOnly = true)
	Optional<Usuario> findByEmail(String email);

	@Transactional(readOnly = true)
	Optional<Usuario> findByCpf(String cpf);

	Optional<Usuario> findByIdOrCpf(Long id, String cpf);

	@Modifying(clearAutomatically = true)
	@Transactional(readOnly = false)
	@Query("UPDATE Usuario u SET u.ativo = :ativo WHERE u.id = :id")
	int updateAtivoById(@Param("ativo") Boolean ativo, @Param("id") Long id);

	@Modifying(clearAutomatically = true)
	@Transactional(readOnly = false)
	@Query("UPDATE Usuario u SET u.senha = :senha WHERE u.id = :id")
	void updateSenhaById(@Param("id") Long id, @Param("senha") String senha);
}
