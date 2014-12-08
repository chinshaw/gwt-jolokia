package com.google.gwt.jolokia.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class JolokiaRequest {
	
	private String mbean;
	
	private String attribute;
	
	public String getMbean() {
		return mbean;
	}

	public void setMbean(String mbean) {
		this.mbean = mbean;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
}
