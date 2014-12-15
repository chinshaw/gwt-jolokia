package com.google.gwt.jolokia.client;

public class JolokiaListRequest extends JolokiaRequest {

	protected JolokiaListRequest(){}
	
	public static final JolokiaListRequest create() {
		JolokiaListRequest request = createObject().cast();
		request.setType("list");
		return request;
	}
}
