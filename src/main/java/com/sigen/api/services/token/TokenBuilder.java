package com.sigen.api.services.token;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Construtor de token simples, testado e ok
 * @author lucas
 *
 */
public interface TokenBuilder {
	static final ObjectMapper serializer = new ObjectMapper();

	static final Encoder encoder = Base64.getEncoder();

	static final Decoder decoder = Base64.getDecoder();

	public static String encode(TokenUsuario token) {

		try {
			byte[] data = serializer.writeValueAsString(token).getBytes();
			String base64Token = encoder.encodeToString(data);
			return base64Token;
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Falha ao construir token");
		}
	}

	public static TokenUsuario decode(String data) {

		String json = new String(decoder.decode(data));

		try {
			TokenUsuario token = serializer.readValue(json, TokenUsuario.class);
			return token;
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException("Falha ao decodificar token");
		}
	}
}
