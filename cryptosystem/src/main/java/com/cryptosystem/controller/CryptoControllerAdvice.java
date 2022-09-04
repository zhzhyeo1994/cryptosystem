package com.cryptosystem.controller;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cryptosystem.exception.InsufficientValueException;
import com.cryptosystem.exception.PriceChangedException;

@ControllerAdvice
public class CryptoControllerAdvice extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(InsufficientValueException.class)
	public ResponseEntity<Object> InsufficientValueException(InsufficientValueException ex){
	     Map<String, Object> body = new LinkedHashMap<>();
	        body.put("timestamp", LocalDateTime.now());
	        body.put("message", ex.getMessage());
		return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(PriceChangedException.class)
	public ResponseEntity<Object> PriceChangesException(PriceChangedException ex){
	     Map<String, Object> body = new LinkedHashMap<>();
	        body.put("timestamp", LocalDateTime.now());
	        body.put("message", ex.getMessage());
		return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);
	}
}
