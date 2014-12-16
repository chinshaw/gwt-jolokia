package com.google.gwt.jolokia.client;

import com.google.gwt.core.client.JsArray;

public class JolokiaMultiResponse extends JsArray<JolokiaMultiResponse> {

	
	protected JolokiaMultiResponse() {
	}
	
	public static final JolokiaMultiResponse create() {
		return createArray().cast();
	}
}
