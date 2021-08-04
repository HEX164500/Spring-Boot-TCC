package com.sigen.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sigen.api.dto.CompraDTO;
import com.sigen.api.entities.Compra;
import com.sigen.api.entities.Endereco;
import com.sigen.api.entities.Usuario;
import com.sigen.api.enums.EstadoPagamento;
import com.sigen.api.exceptions.NotFoundException;
import com.sigen.api.repositories.CompraRepository;
import com.sigen.api.repositories.EnderecoRepository;
import com.sigen.api.repositories.ProdutoRepository;
import com.sigen.api.repositories.UsuarioRepository;

@Service
public class CompraService {

	@Autowired
	private CompraRepository repository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private EnderecoRepository enderecoRepo;

	@Autowired
	private ProdutoRepository produtoRepo;

	@Transactional(readOnly = true)
	public CompraDTO findById(Long id) {
		Compra compra = repository.findById(id).orElseThrow(() -> new NotFoundException("Compra não encontrada"));
		return new CompraDTO(compra);
	}

	@Transactional(readOnly = true)
	public Page<CompraDTO> findAllByUsuarioAndEstado(Long idUsuario, EstadoPagamento estado, Pageable page) {
		Usuario usuario = new Usuario();
		usuario.setId(idUsuario);
		return repository.findAllByUsuarioAndEstado(usuario, estado, page).map(compra -> new CompraDTO(compra));
	}

	@Transactional
	public CompraDTO save(Compra c, Long idUsuario) {
		Usuario usuario = usuarioRepository.findById(idUsuario)
				.orElseThrow(() -> new NotFoundException("Usuario não encontrado"));

		if (c.getItems().size() < 1) {
			throw new IllegalArgumentException("Uma compra deve conter ao menos 1 produto");
		}

		if (c.getEndereco() == null) {
			throw new NotFoundException("Endereço deve ser fornecido na compra");
		}
		if (c.getEndereco().getId() == null) {
			throw new NotFoundException("Endereço deve ser fornecido na compra e deve ter um ID");
		}

		Endereco e = enderecoRepo.findById(c.getEndereco().getId()).orElse(null);

		if (e == null) {
			throw new NotFoundException("Endereço não pode ser encontrado");
		}

		c.setEndereco(e);
		// necessário primeira a compra salva
		c.setUsuario(usuario);
		
		c.getItems().forEach(item -> {
			var produto = produtoRepo.findById(item.getProduto().getId()).orElse(null);
			item.setProduto(produto);
		});

		Compra compra = repository.saveAndFlush(c);
		
		Compra retorno = repository.findById(compra.getId()).orElse(null);
		retorno.calcularTotal();

		// salvamos a entidade e recarregamos
		repository.saveAndFlush(retorno);
		return new CompraDTO(retorno);
	}

	@Transactional
	public void cancelar(Long id, Long idUsuario) {
		Compra compra = repository.findById(id).orElseThrow(() -> new NotFoundException("Compra não encontrada"));

		if (compra.getUsuario().getId() != idUsuario)
			throw new AccessDeniedException("Não autorizado");

		if (compra.getEstado().equals(EstadoPagamento.COMPLETO) || compra.getEstado().equals(EstadoPagamento.CANCELADO))
			throw new IllegalStateException("Compra já concluida ou cancelada");

		compra.cancelar();
		repository.saveAndFlush(compra);
	}

	@Transactional
	public void completar(Long id) {
		Compra compra = repository.findById(id).orElseThrow(() -> new NotFoundException("Compra não encontrada"));

		if (compra.getEstado().equals(EstadoPagamento.COMPLETO) || compra.getEstado().equals(EstadoPagamento.CANCELADO))
			throw new IllegalStateException("Compra já concluida ou cancelada");

		compra.completar();
		repository.saveAndFlush(compra);
	}
}
