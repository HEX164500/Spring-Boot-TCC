package com.sigen.api.enums;

public enum Genero {
	HOMEM("HOMEM"), MULHER("MULHER"), OUTRO("OUTRO");
	
	private String genero;
	
	private Genero(String genero) {
		this.genero = genero;
	}
	
	@Override
	public String toString() {
		return genero;
	}
}
