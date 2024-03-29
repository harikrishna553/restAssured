package com.sample.app.model;

import java.util.HashMap;
import java.util.Map;

public class RequestInfo {
	private Map<String, String> headers = new HashMap<>();
	private Map<String, String[]> parameters = new HashMap<>();
	private Map<String, Object> attributes = new HashMap<>();
	private String payload;
	
	public RequestInfo() {
		
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public Map<String, String[]> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, String[]> parameters) {
		this.parameters = parameters;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

}
