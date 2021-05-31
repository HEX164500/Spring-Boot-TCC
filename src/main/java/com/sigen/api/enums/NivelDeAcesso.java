package com.sigen.api.enums;

public enum NivelDeAcesso {

	USUARIO("USUARIO"), EMPREGADO("EMPREGADO");

	private String access;

	private NivelDeAcesso(String access) {
		this.access = access;
	}

	@Override
	public String toString() {
		return access;
	}
}
