package com.google.gwt.jolokia.client.rest;

import java.util.logging.Logger;

import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.jolokia.client.JolokiaListRequest;
import com.google.gwt.jolokia.client.JolokiaListResponse;
import com.google.gwt.jolokia.client.JolokiaReadRequest;
import com.google.gwt.jolokia.client.JolokiaReadResponse;
import com.google.gwt.jolokia.client.JolokiaRequest;
import com.google.gwt.jolokia.client.JolokiaResponse;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class JolokiaClient {

	private static final Logger logger = Logger.getLogger(JolokiaClient.class.getName());
	
	private final RequestBuilder requestBuilder;
	
	public JolokiaClient(final String url) {
		requestBuilder = new RequestBuilder(RequestBuilder.POST, url);
	}
	
	public JolokiaClient(final String url, final String username, final String password) {
		this(url);
		requestBuilder.setUser(username);
		requestBuilder.setPassword(password);
		requestBuilder.setHeader("Authorization", createBasicAuthToken(username, password));
	}

	public void getAttribute(String mbean, String attribute, String path, String[] opts,
			final AsyncCallback<JolokiaReadResponse> callback) throws RequestException {
		
		JolokiaReadRequest readRequest = JolokiaReadRequest.create();
		readRequest.setMbean(mbean);
		if (path != null) {
			readRequest.setPath(path);
		}
		if (attribute != null) {
			readRequest.setAttribute(attribute);
		}
		send(readRequest, new AsyncCallback<JolokiaResponse>() {

			@Override
			public void onFailure(Throwable caught) {
				callback.onFailure(caught);
			}

			@Override
			public void onSuccess(JolokiaResponse result) {
				callback.onSuccess((JolokiaReadResponse)result.cast());
			}
		});
	}
	
	
	public void listMbeans(final AsyncCallback<JolokiaListResponse> callback) throws RequestException {
		list(JolokiaListRequest.create(), callback);
	}
	
	public void list(final JolokiaRequest request, final AsyncCallback<JolokiaListResponse> callback) throws RequestException {
		
		send(request, new AsyncCallback<JolokiaResponse>() {

			@Override
			public void onFailure(Throwable caught) {
				callback.onFailure(caught);
			}

			@Override
			public void onSuccess(JolokiaResponse result) {
				callback.onSuccess((JolokiaListResponse)result.cast());
			}
		});
	}

	final void send(final JolokiaRequest request, final AsyncCallback<JolokiaResponse> callback) throws RequestException {
		final String jsonRequest = new JSONObject(request).toString();
		logger.info("request json  "+ " \n " + jsonRequest);
		
		requestBuilder.sendRequest(jsonRequest, new RequestCallback() {
			
			@Override
			public void onResponseReceived(Request request, Response response) {
				if (response.getStatusCode() == 200) {
					JolokiaResponse jolokiaResponse = JsonUtils.safeEval(response.getText());
					callback.onSuccess(jolokiaResponse);
				}
			}
			
			@Override
			public void onError(Request request, Throwable exception) {
				callback.onFailure(exception);
			}
		});
	}
	
	protected String createBasicAuthToken(final String username, final String password) {
	    byte[] bytes = stringToBytes(username + ":" + password);
	    String token = Base64Utils.toBase64(bytes);
	    return "Basic " + token;
	}
	
	protected byte[] stringToBytes(String msg) {
	    int len = msg.length();
	    byte[] bytes = new byte[len];
	    for (int i = 0; i < len; i++)
	        bytes[i] = (byte) (msg.charAt(i) & 0xff);
	    return bytes;
	}
}
