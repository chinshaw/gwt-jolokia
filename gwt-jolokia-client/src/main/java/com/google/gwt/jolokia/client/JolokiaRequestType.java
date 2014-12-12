package com.google.gwt.jolokia.client;

public enum JolokiaRequestType {

	READ("read"),
	WRITE("write"),
	EXEC("exec"),
	SEARCH("search");
	
	private String type;
	
	JolokiaRequestType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}
