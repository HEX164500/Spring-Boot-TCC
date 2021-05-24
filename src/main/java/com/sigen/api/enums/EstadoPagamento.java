package com.sigen.api.enums;

public enum EstadoPagamento {

	COMPLETO("COMPLETO"),
	PENDENTE("PENDENTE"),
	CANCELADO("CANCELADO");
	
	String estado;
	
	private EstadoPagamento(String estado) {
		this.estado = estado;
	}
	
	@Override
	public String toString() {
		return estado;
	}
}
