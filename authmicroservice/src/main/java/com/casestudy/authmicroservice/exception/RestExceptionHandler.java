package com.casestudy.authmicroservice.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.casestudy.authmicroservice.model.MessageResponse;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler {

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(UnauthorisedException.class)
	public ResponseEntity<?> handleUnauthorizedExceptions(UnauthorisedException ex) {

		log.error("Unauthorized request");
		return ResponseEntity.badRequest()
				.body(new MessageResponse("Unauthorized request. Login again...", "Unauthorized"));
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MissingRequestHeaderException.class)
	public ResponseEntity<?> handleMissingRequestHeaderException(MissingRequestHeaderException ex) {

		log.error("Required Bearer token");
		return ResponseEntity.badRequest().body(new MessageResponse("Required Bearer token", "Bad Request"));
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity<?> handleExpiredJwtException(ExpiredJwtException ex) {

		log.error("Token has expired");
		return ResponseEntity.badRequest().body(new MessageResponse("Token has expired", "Bad Request"));
	}

}

