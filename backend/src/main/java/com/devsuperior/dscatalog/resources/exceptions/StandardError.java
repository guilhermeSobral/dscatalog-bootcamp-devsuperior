package com.devsuperior.dscatalog.resources.exceptions;

import java.io.Serializable;
import java.time.Instant;

public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Instant timestamp;
	private Integer status;
	private String error;
	private String menssage;
	private String path;
	
	public StandardError(Instant timestamp, Integer status, String error, String menssage, String path) {
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.menssage = menssage;
		this.path = path;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public String getError() {
		return error;
	}

	public String getMenssage() {
		return menssage;
	}

	public String getPath() {
		return path;
	}	
}
