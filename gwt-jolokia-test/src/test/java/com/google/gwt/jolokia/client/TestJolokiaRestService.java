package com.google.gwt.jolokia.client;

import org.fusesource.restygwt.client.JsonEncoderDecoder;
import org.junit.Test;

import com.google.gwt.core.client.GWT;
import com.google.gwt.jolokia.client.rest.JolokiaRestRequest;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class TestJolokiaRestService extends GWTTestCase {


	public interface MemoryDecoder extends JsonEncoderDecoder<JolokiaMemoryResponse>{}
	
	
	@Override
	public String getModuleName() {
		return "com.google.gwt.jolokia.JolokiaTest";
	}
	
	@Test
	public void testGetMemory() {
		UserCredentials.INSTANCE.setUserName("admin");
		UserCredentials.INSTANCE.setPassword("admin");
		JolokiaRestRequest<JolokiaMemoryResponse> memoryRequest = new JolokiaRestRequest<JolokiaMemoryResponse>();
		JolokiaReadRequest readRequest = new JolokiaReadRequest();
		readRequest.setMbean("java.lang:type=Memory");
		memoryRequest.read(readRequest,(MemoryDecoder)GWT.create(MemoryDecoder.class), new AsyncCallback<JolokiaMemoryResponse>() {
			
			public void onSuccess(JolokiaMemoryResponse response) {
				assertTrue(response.getNonHeapMemoryUsage() != null);
				assertTrue(response.getNonHeapMemoryUsage().getUsed() > 0);
			}
			
			public void onFailure(Throwable exception) {
				fail("testGetMemory" + exception.getMessage());
			}
		} );
	}
}