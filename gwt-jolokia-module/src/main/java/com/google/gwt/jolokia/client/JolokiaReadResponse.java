package com.google.gwt.jolokia.client;


public class JolokiaReadResponse extends JolokiaResponse {
	
	private JolokiaReadRequest readRequest;
	
	public JolokiaReadRequest getReadRequest() {
		return readRequest;
	}

	public void setReadRequest(JolokiaReadRequest readRequest) {
		this.readRequest = readRequest;
	}

}
