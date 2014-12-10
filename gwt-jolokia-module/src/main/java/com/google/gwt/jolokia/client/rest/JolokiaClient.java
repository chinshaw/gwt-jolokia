package com.google.gwt.jolokia.client.rest;

import java.util.logging.Logger;

import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.jolokia.client.JolokiaReadRequest;
import com.google.gwt.jolokia.client.JolokiaReadResponse;
import com.google.gwt.jolokia.client.JolokiaRequest;
import com.google.gwt.jolokia.client.JolokiaResponse;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class JolokiaClient {

	private static final Logger logger = Logger.getLogger(JolokiaClient.class.getName());
	
	private final RequestBuilder builder;
	
	public JolokiaClient(final String url) {
		builder = new RequestBuilder(RequestBuilder.POST, url);
	}
	
	public JolokiaClient(final String url, final String username, final String password) {
		builder = new RequestBuilder(RequestBuilder.POST, url);
		builder.setUser(username);
		builder.setPassword(password);
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

	final void send(final JolokiaRequest request, final AsyncCallback<JolokiaResponse> callback) throws RequestException {
		final String jsonRequest = new JSONObject(request).toString();
		logger.info("request json  "+ " \n " + jsonRequest);
		
		builder.sendRequest(jsonRequest, new RequestCallback() {
			
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

}
