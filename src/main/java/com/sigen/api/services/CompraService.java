package com.sigen.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sigen.api.dto.CompraDTO;
import com.sigen.api.entities.Compra;
import com.sigen.api.entities.Usuario;
import com.sigen.api.enums.EstadoPagamento;
import com.sigen.api.exceptions.NotFoundException;
import com.sigen.api.repositories.CompraRepository;
import com.sigen.api.repositories.ItemCompraRepository;
import com.sigen.api.repositories.UsuarioRepository;

@Service
public class CompraService {

	@Autowired
	private CompraRepository repository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ItemCompraRepository itemCompraRepository;

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

		// necessário primeira a compra salva
		c.setUsuario(usuario);
		Compra compra = repository.saveAndFlush(c);

		compra.getItems().forEach(item -> {
			item.setCompra(compra);
		});

		// para depois salvar os items
		compra.getItems().clear();
		compra.setItems(itemCompraRepository.saveAll(compra.getItems()));

		// recarregamos a entidade e calculamos seu total pois agora seus items estão
		// presentes
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
