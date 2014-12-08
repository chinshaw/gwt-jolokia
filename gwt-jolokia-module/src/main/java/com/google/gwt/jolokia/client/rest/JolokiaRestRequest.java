package com.google.gwt.jolokia.client.rest;

import org.fusesource.restygwt.client.JsonEncoderDecoder;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.google.gwt.jolokia.client.JolokiaReadRequest;
import com.google.gwt.jolokia.client.JolokiaResponse;
import com.google.gwt.jolokia.client.JolokiaRestService;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class JolokiaRestRequest<T extends JolokiaResponse> {

	private static final JolokiaRestService service = JolokiaRestService.Util.get();
	
	public void read(JolokiaReadRequest request,final JsonEncoderDecoder<T> decoder, final AsyncCallback<T> asyncCallback) {
		service.read(request, new MethodCallback<Void>() {

			public void onFailure(Method method, Throwable exception) {
				asyncCallback.onFailure(exception);
			}

			public void onSuccess(Method method, Void response) {
				String body = method.getResponse().getText();
				System.out.println("body is " + body);
				asyncCallback.onSuccess(decoder.decode(body) );
			}
		});
	}
	
}
