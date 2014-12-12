package com.google.gwt.jolokia.client;



public class JolokiaReadRequest extends JolokiaRequest {

	
	protected JolokiaReadRequest() {
	}
	
	
	public final native String getPath() /*-{
		return this.path;
	}-*/;

	public final native void setPath(String path) /*-{
		this.path = path;
	}-*/;
	
	public static final JolokiaReadRequest create() {
		JolokiaReadRequest readRequest = createObject().cast();
		readRequest.setType("read");
		return readRequest;
	}
}
