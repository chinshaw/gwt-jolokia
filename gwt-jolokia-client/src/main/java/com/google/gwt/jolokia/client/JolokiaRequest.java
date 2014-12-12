package com.google.gwt.jolokia.client;

import com.google.gwt.core.client.JavaScriptObject;

public class JolokiaRequest extends JavaScriptObject {

	protected JolokiaRequest() {
	}
	
	protected final native void setType(String type) /*-{
		this.type = type;
	}-*/;
	
	public final native void getType() /*-{
		return this.type;
	}-*/;
	
	public final native String getMbean() /*-{
		return this.mbean;
	}-*/;

	public final native void setMbean(String mbean) /*-{
		this.mbean = mbean;
	}-*/;

	public final native String getAttribute() /*-{
		return this.attribute;
	}-*/;

	public final native void setAttribute(String attribute) /*-{
		this.attribute = attribute;
	}-*/;
}