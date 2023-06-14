package com.exemplo.os.resource.exceptions;

import java.util.ArrayList;

import jakarta.validation.constraints.AssertFalse.List;

public class ValidationError extends StandardError {	
	private static final long serialVersionUID = 1L;

	private java.util.List<FieldMessade> erros = new ArrayList<>();

	public ValidationError() {
		super();		
	}

	public ValidationError(Long timestamp, Integer status, String error) {
		super(timestamp, status, error);		
	}

	public java.util.List<FieldMessade> getErros() {
		return erros;
	}

	//public void setErros(java.util.List<FieldMessade> erros) {
	//	this.erros = erros;
	//}
	
	public void addError(String fieldName, String message) {
		this.erros.add(new FieldMessade(fieldName, message));
	}
	
	
	

}
