package com.exemplo.os.resource.exceptions;

import java.io.Serializable;

public class FieldMessade implements Serializable{	
	private static final long serialVersinUID = 1L;
	
	
	private String FieldName;
	private String message;
	
	
	public FieldMessade() {
		super();
		// TODO Auto-generated constructor stub
	}


	public FieldMessade(String fieldName, String message) {
		super();
		FieldName = fieldName;
		this.message = message;
	}


	public String getFieldName() {
		return FieldName;
	}


	public void setFieldName(String fieldName) {
		FieldName = fieldName;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}

		
}
