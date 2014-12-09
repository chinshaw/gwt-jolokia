package com.google.gwt.jolokia.client;

import com.google.gwt.core.client.JavaScriptObject;


public class JolokiaReadResponse extends JolokiaResponse {
	
	protected JolokiaReadResponse() {
		
	}
	
	public final native JavaScriptObject getValue() /*-{
		return this.value;
	}-*/;
	
	public final native <T extends JavaScriptObject> T getValuePropertyObj(String key) /*-{
		return this.value[key];
	}-*/;
	
	public final native int getValuePropertyInt(String key) /*-{
		return this.value[key];
	}-*/;
}
