package com.sigen.api.enums;

public enum AccessLevel {

	USUARIO("USUARIO"), EMPREGADO("EMPREGADO"), GERENTE("GERENTE"), SUPORTE("SUPORTE"), ADIMISTRADOR("ADIMISTRADOR");

	private String access;

	private AccessLevel(String access) {
		this.access = access;
	}

	@Override
	public String toString() {
		return access;
	}
}
