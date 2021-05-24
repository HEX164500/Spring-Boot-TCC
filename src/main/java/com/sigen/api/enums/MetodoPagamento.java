package com.sigen.api.enums;

public enum MetodoPagamento {

	BOLETO("BOLETO");

	private String metodo;

	private MetodoPagamento(String metodo) {
		this.metodo = metodo;
	}

	@Override
	public String toString() {
		return metodo;
	}
}
