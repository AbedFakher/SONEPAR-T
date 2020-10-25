package com.biskot.domain.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final String message;
    private final Object[] args;
    
    public NotFoundException(String message, Object... args) {
        this.message = message;
        this.args = args;
    }
}
