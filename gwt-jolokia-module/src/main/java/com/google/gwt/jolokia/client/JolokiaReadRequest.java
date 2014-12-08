package com.google.gwt.jolokia.client;

import com.fasterxml.jackson.annotation.JsonProperty;


public class JolokiaReadRequest extends JolokiaRequest {

	private String path;
	
	@JsonProperty("type")
	private String type = JolokiaRequestType.READ.getType();

	public String getType() {
		return JolokiaRequestType.READ.getType();
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void addAttribute(String key, String value) {
		// TODO Auto-generated method stub
		
	}
}
