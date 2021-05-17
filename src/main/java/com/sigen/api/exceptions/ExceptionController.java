package com.sigen.api.exceptions;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestControllerAdvice
public class ExceptionController {

	private static ObjectMapper serializer = new ObjectMapper();
	
	@ExceptionHandler( NotFoundException.class )
	public ResponseEntity<String> handleNotFoundException( NotFoundException e ) {
		return createMessage(e, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler( ConstraintViolationException.class )
	public ResponseEntity<String> handleConstraintViolationException( ConstraintViolationException e ) {
		return createMessage(e, HttpStatus.BAD_REQUEST);
	}

	private static ResponseEntity<String> createMessage(Exception e, HttpStatus status) {
		Map<String, String> body = new HashMap<>(3);

		body.put("title", "error");
		body.put("message", e.getMessage());
		body.put("Time", LocalDate.now().toString());

		try {
			return ResponseEntity.status(status).body(serializer.writeValueAsString(body));
		} catch (JsonProcessingException e1) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("{\"message\":\"Erro ao processar resposta\"");
		}
	}
}
