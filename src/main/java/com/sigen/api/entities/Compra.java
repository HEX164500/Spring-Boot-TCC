package com.sigen.api.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.sigen.api.enums.EstadoPagamento;
import com.sigen.api.enums.MetodoPagamento;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "compras")
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_usuario", updatable = false)
	@JsonProperty(access = Access.READ_ONLY)
	private Usuario usuario;

	@JsonProperty(value = "datacompra", access = Access.READ_ONLY)
	private LocalDate dataCompra = LocalDate.now();

	@JsonProperty(access = Access.READ_ONLY)
	private Double total;

	@Enumerated(EnumType.STRING)
	@JsonProperty(access = Access.READ_ONLY)
	private EstadoPagamento estado = EstadoPagamento.PENDENTE;

	@Enumerated(EnumType.STRING)
	@JsonProperty(access = Access.READ_ONLY)
	@Column(updatable = false, nullable = false)
	private MetodoPagamento metodo = MetodoPagamento.BOLETO;

	@JsonProperty(value = "datapagamento", access = Access.READ_ONLY)
	private LocalDate dataPagamento;

	@ManyToOne(optional = false, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_endereco", updatable = false)
	private Endereco endereco;

	@OneToMany(mappedBy = "compra", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private final List<ItemCompra> items = new ArrayList<>();

	public Compra(Long id) {
		this.id = id;
	}

	public void setItems(List<ItemCompra> items) {
		this.items.addAll(items);
	}

	public void cancelar() {
		estado = EstadoPagamento.CANCELADO;
	}

	public void completar() {
		estado = EstadoPagamento.COMPLETO;
		dataPagamento = LocalDate.now();
	}

	public void calcularTotal() {
		total = items.stream().reduce(0D, (acumulador, item) -> {
			return acumulador + item.getProduto().getValor() * item.getQuantidade();
		}, Double::sum);
	}
}
