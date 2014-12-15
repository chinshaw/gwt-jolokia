package com.google.gwt.jolokia.client;

import com.google.gwt.core.client.JavaScriptObject;

public class JolokiaListResponse extends JolokiaResponse {

	protected JolokiaListResponse() {}
	
	public final native JavaScriptObject getValue() /*-{
		return this.value;
	}-*/;
}
