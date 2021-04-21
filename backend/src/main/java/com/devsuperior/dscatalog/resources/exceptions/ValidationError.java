package com.devsuperior.dscatalog.resources.exceptions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> errors = new ArrayList<>();

	public ValidationError(Instant timestamp, Integer status, String error, String menssage, String path) {
		super(timestamp, status, error, menssage, path);
	}

	public List<FieldMessage> getErrors() {
		return Collections.unmodifiableList(errors);
	}
	
	public void addError(String fieldName, String message) {
		errors.add(new FieldMessage(fieldName, message));
	}

}
