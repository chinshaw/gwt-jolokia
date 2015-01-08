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
	
	public static final JolokiaReadRequest create(String mbean) {
		JolokiaReadRequest readRequest = create();
		readRequest.setMbean(mbean);
		return readRequest;
	}
	
	public static final JolokiaReadRequest create(String mbean, String attribute) {
		JolokiaReadRequest readRequest = create(mbean);
		readRequest.setAttribute(attribute);
		return readRequest;
	}
}
