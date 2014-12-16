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
import com.google.gwt.jolokia.client.JolokiaMultiRequest;
import com.google.gwt.jolokia.client.JolokiaMultiResponse;
import com.google.gwt.jolokia.client.JolokiaReadRequest;
import com.google.gwt.jolokia.client.JolokiaReadResponse;
import com.google.gwt.jolokia.client.JolokiaRequest;
import com.google.gwt.jolokia.client.JolokiaResponse;
import com.google.gwt.jolokia.server.servlet.ProxyServlet;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Jolokia client will allow you to make calls to a specific jolokia
 * service. This client is limited to the single origin so you
 * may need to add the {@link ProxyServlet} to your web.xml in order
 * to proxy requests. By default this will support both types of authentication
 * it will support the Authorization header along with the normal 
 * http://user:password@url. Under the covers it uses GWT's RequestBuilder to
 * implement the async support. 
 * 
 * @author chris
 *
 */
public class JolokiaClient {

	private static final Logger logger = Logger.getLogger(JolokiaClient.class.getName());
	
	private final RequestBuilder requestBuilder;
	
	/**
	 * Constructor that will initialize the client url. If the url is not from the
	 * same origin as the jolokia client resides. You will need to implement the
	 * ProxyServlet to handle request redirects.
	 * 
	 * @param url
	 */
	public JolokiaClient(final String url) {
		requestBuilder = new RequestBuilder(RequestBuilder.POST, url);
	}
	
	/**
	 * Constructor that will initialize the client url and authentication parameters. If the url is not
	 * from the same origin as the jolokia client you will need to use the ProxyServlet to handle request
	 * redirects.
	 * 
	 * @param url Full url to where the jolokia server is located or the url of the {@link ProxyServlet}
	 * @param username The username to authenticate to the jolokia service.
	 * @param password The password to authenticate to the jolokia service
	 */
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
	
	/**
	 * This is used to do a simple jolokia list method. This will by default list all
	 * the mbean objects with their children from the server. This could be used to
	 * generate a tree of objects.
	 * @param callback
	 * @throws RequestException
	 */
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
	
	/**
	 * Send a JolokiaMultiRequest. This allows you to send a single request with multiple
	 * {@link JolokiaReadRequest} objects embedded in it. The response will be  {@link JolokiaMultiResponse}
	 * wrapped in an async callback. The order of the response should be the exact same as the
	 * order of requests.
	 * 
	 * @param request
	 * @param callback
	 * @throws RequestException
	 */
	/*
	 * TODO this is only call the send method but need to investigate how to combine both of the send methods for
	 * a cleaner implementation. Right now it won't work due to type erasure of the callback.
	 */
	public void sendMulti(JolokiaMultiRequest request, final AsyncCallback<JolokiaMultiResponse> callback) throws RequestException {
		send(request, callback);
	}
	
	/**
	 * Send a JolokiaMultiRequest. This allows you to send a single request with multiple
	 * {@link JolokiaReadRequest} objects embedded in it. The response will be  {@link JolokiaMultiResponse}
	 * wrapped in an async callback. The order of the response should be the exact same as the
	 * order of requests.
	 * 
	 * @param request
	 * @param callback
	 * @throws RequestException
	 */
	final void send(JolokiaMultiRequest request, final AsyncCallback<JolokiaMultiResponse> callback) throws RequestException {
		final String jsonRequest = new JSONArray(request).toString();
		logger.info("Multi request payload " + jsonRequest);
		requestBuilder.sendRequest(jsonRequest, new RequestCallback() {
			
			@Override
			public void onResponseReceived(Request request, Response response) {
				if (response.getStatusCode() == 200) {
					JolokiaMultiResponse jolokiaResponse = JsonUtils.safeEval(response.getText());
					callback.onSuccess(jolokiaResponse);
				}
			}
			
			@Override
			public void onError(Request request, Throwable exception) {
				callback.onFailure(exception);
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
	
	/**
	 * Internal method to create an basic authentication token to be
	 * used for the Authorization header.
	 * @param username
	 * @param password
	 * @return
	 */
	private String createBasicAuthToken(final String username, final String password) {
	    byte[] bytes = stringToBytes(username + ":" + password);
	    String token = Base64Utils.toBase64(bytes);
	    return "Basic " + token;
	}
	
	/**
	 * Utility function to convert string to byte array.
	 * @param msg
	 * @return
	 */
	private byte[] stringToBytes(String msg) {
	    int len = msg.length();
	    byte[] bytes = new byte[len];
	    for (int i = 0; i < len; i++)
	        bytes[i] = (byte) (msg.charAt(i) & 0xff);
	    return bytes;
	}
}
