package com.google.gwt.jolokia.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.core.client.UnsafeNativeLong;


public class JolokiaReadResponse extends JolokiaResponse {
	
	protected JolokiaReadResponse() {}
	
	public final native JavaScriptObject getValue() /*-{
		return this.value;
	}-*/;
	
	public final native <T extends JavaScriptObject> T getValuePropertyObj(String key) /*-{
		return this.value[key];
	}-*/;
	
	public final native int getValuePropertyInt(String key) /*-{
		return this.value[key];
	}-*/;
	
	@UnsafeNativeLong
	public final native long getValuePropertyLong(String key) /*-{
		return this.value[key];
	}-*/;
	
	public final native String getValuePropertyString(String key) /*-{
		return this.value[key];
	}-*/;
	
	public final native JsArrayString getValueProperties() /*-{
		return Object.keys(this.value);
	}-*/;
}