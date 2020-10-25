package com.biskot.app.rest.handler;


import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.biskot.domain.exception.BusinessException;
import com.biskot.domain.exception.NotFoundException;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
@AllArgsConstructor
public class RestExceptionHandler {
	
	private static final String UNEXPECTED_ERROR = "Exception.unexpected";

	@Autowired
	private final MessageSource messageSource;

	@ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<Error> handleBusinessException(BusinessException ex) {
        String errorMessage = messageSource.getMessage(ex.getMessage(), ex.getArgs(), Locale.getDefault());
		log.error("business error : {}"  , errorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        		//.header("error_message" , errorMessage )
        		.body(Error.builder().code(ex.getMessage()).message(errorMessage).build());
    }
	
	@ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Error> handleNotFoundException(NotFoundException ex) {
        String errorMessage = messageSource.getMessage(ex.getMessage(), ex.getArgs(), Locale.getDefault());
		log.error("not found error : {}"  , errorMessage);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
        		//.header("error_message" , errorMessage )
        		.body(Error.builder().code(ex.getMessage()).message(errorMessage).build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleExceptions(Exception exception) {
        String errorMessage = messageSource.getMessage(UNEXPECTED_ERROR, null, Locale.getDefault());
        log.error("error : {}"  , exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        		//.header("error_message" , errorMessage )
        		.body(Error.builder().code(UNEXPECTED_ERROR).message(errorMessage).build());
    }

}