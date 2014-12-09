package com.google.gwt.jolokia.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.UnsafeNativeLong;

public class JolokiaResponse extends JavaScriptObject {

	protected JolokiaResponse() {
		
	}
	
	@UnsafeNativeLong
	public final native long getTimestamp() /*-{
		return this.timestamp;
	}-*/;
	
	@UnsafeNativeLong
	public final native void setTimestamp(long timestamp) /*-{
		this.timestamp = timestamp;
	}-*/;

	public final native int getStatus() /*-{
		return this.status;
	}-*/;

	public final native void setStatus(int status) /*-{
		this.status = status;
	}-*/;

	public final native JolokiaRequest getRequest() /*-{
		return this.request;
	}-*/;

	public final native void setRequest(JolokiaRequest request) /*-{
		this.request = request;
	}-*/;

	
	public final native String getErrorType() /*-{
		return this.errorType;
	}-*/;

	public final native void setErrorType(String errorType) /*-{
		this.errorType = errorType;
	}-*/;

	public final native String getError() /*-{
		return this.error;
	}-*/;

	public final native void setError(String error) /*-{
		this.error = error;
	}-*/;

	public final native String getStacktrace() /*-{
		return this.stacktrace;
	}-*/;

	public final native void setStacktrace(String stacktrace) /*-{
		this.stacktrace = stacktrace;
	}-*/;
	
}
