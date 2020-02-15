package com.service.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import com.service.exception.ProductDoesNotExistsException;
import com.service.exception.InternalServerError;
import com.service.model.ErrorModel;

@ControllerAdvice
@RequestMapping(produces = "application/json")
public class RestErrorHandler {
	
	private static final String INTERNAL_SERVER_ERROR_LOG = "Internal Server Error - ";
	private static final Logger LOGGER = Logger.getLogger(RestErrorHandler.class.getName());
	
	@ExceptionHandler(InternalServerError.class)
	public ResponseEntity<ErrorModel> hadleInternalError(final InternalServerError e) {
		LOGGER.log(Level.WARNING,INTERNAL_SERVER_ERROR_LOG+e.getErrorModel());
		return error(e.getErrorModel(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ProductDoesNotExistsException.class)
	public ResponseEntity<ErrorModel> hadleInternalError(final ProductDoesNotExistsException e) {
		LOGGER.log(Level.WARNING,INTERNAL_SERVER_ERROR_LOG+e.getErrorModel());
		return error(e.getErrorModel(), HttpStatus.NOT_FOUND);
	}
	
	private ResponseEntity<ErrorModel> error(final ErrorModel em, HttpStatus httpStatus) {
		return new ResponseEntity<ErrorModel>(em, httpStatus);
	}

	
}
