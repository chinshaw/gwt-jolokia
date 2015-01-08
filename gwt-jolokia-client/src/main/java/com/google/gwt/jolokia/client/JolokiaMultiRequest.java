package com.google.gwt.jolokia.client;

import com.google.gwt.core.client.JsArray;

public class JolokiaMultiRequest extends JsArray<JolokiaRequest> {
	
	protected JolokiaMultiRequest() {
	}
	
	public static final JolokiaMultiRequest create() {
		return createArray().cast();
	}
	
	public static final JolokiaMultiRequest create(JolokiaRequest... requests) {
		JolokiaMultiRequest multiRequest = create();
		for (JolokiaRequest request : requests) {
			multiRequest.push(request);
		}
		return multiRequest;
	}
}
