package com.sigen.api.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.PropertyValueException;
import org.hibernate.QueryException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionController {

	private static ObjectMapper serializer = new ObjectMapper();

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<String> handleNotFoundException(NotFoundException e) {
		return createResponseMessage(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = { HttpMessageNotReadableException.class, PropertyValueException.class,
			QueryException.class })
	public ResponseEntity<String> handleBadRequest(Exception e) {

		return createResponseMessage(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ResponseEntity<String> handleNoHandlerFoundException(NoHandlerFoundException e) {

		return createResponseMessage(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ ConstraintViolationException.class, DataIntegrityViolationException.class })
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ResponseEntity<String> handleJdbcSQLIntegrityConstraintViolationException(Exception e) {

		return createResponseMessage(e.getMessage(),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException e) {

		return createResponseMessage(e.getMessage(), HttpStatus.FORBIDDEN);
	}

	@Order(Ordered.LOWEST_PRECEDENCE)
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleDefaultError(Exception e) {
		return createResponseMessage(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private static ResponseEntity<String> createResponseMessage(String message, HttpStatus status) {
		return ResponseEntity.status(status).body(createSimpleJsonMessage(message));
	}

	public static String createSimpleJsonMessage(String message) {
		Map<String, String> body = new HashMap<>(3);

		body.put("title", "error");
		body.put("message", message);
		body.put("Date", LocalDateTime.now().toString());

		try {
			return serializer.writeValueAsString(body);
		} catch (JsonProcessingException e1) {
			return "{\"message\":\"Erro ao processar resposta\"";
		}
	}
}
