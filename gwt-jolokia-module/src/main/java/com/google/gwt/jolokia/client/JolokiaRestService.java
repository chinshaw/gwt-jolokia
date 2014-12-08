package com.google.gwt.jolokia.client;

import javax.ws.rs.POST;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.RestService;
import org.fusesource.restygwt.client.RestServiceProxy;

import com.google.gwt.core.client.GWT;

public interface JolokiaRestService extends RestService {

	public static final class Util {
		private static JolokiaRestService instance;

		public static final JolokiaRestService get() {
			if (instance == null) {
				instance = GWT.create(JolokiaRestService.class);
				RestServiceProxy service = (RestServiceProxy)instance;
				
				service.setResource(new Resource(GWT.getModuleBaseURL() + "api/jolokia/"));
				System.out.println("URL IS " +GWT.getModuleBaseURL() + "api/jolokia");
				service.setDispatcher(new AuthenticationDispatcher());
			}
			return instance;
		}
	}
	
	@POST
	void read(JolokiaReadRequest readRequest, MethodCallback<Void> callback);
	
	/*
	@POST
	void write(JolokiaWriteRequest writeRequest, MethodCallback<JolokiaWriteResponse> callback);
	
	@POST
	void exec(JolokiaExecRequest execRequest, MethodCallback<JolokiaExecResponse> callback);
	
	@POST
	void search(JolokiaSearchRequest searchRequest, MethodCallback<JolokiaSearchResponse> callback);
	*/
}
