package com.biskot.app.rest.handler;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Error {
	
	private String code;
	private String message;

}
