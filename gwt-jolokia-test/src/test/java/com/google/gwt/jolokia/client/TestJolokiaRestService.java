package com.google.gwt.jolokia.client;

import org.junit.Test;

import com.google.gwt.http.client.RequestException;
import com.google.gwt.jolokia.client.rest.JolokiaClient;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class TestJolokiaRestService extends GWTTestCase {

	
	@Override
	public String getModuleName() {
		return "com.google.gwt.jolokia.JolokiaTest";
	}
	
	@Test
	public void testGetMemory() throws RequestException {
		final JolokiaClient client = new JolokiaClient("/com.google.gwt.jolokia.JolokiaTest.JUnit/api/jolokia"); 

		client.getAttribute("java.lang:type=Memory", "HeapMemoryUsage", null, null, new AsyncCallback<JolokiaReadResponse>() {

			@Override
			public void onFailure(Throwable caught) {
				fail(caught.getMessage());
			}

			@Override
			public void onSuccess(JolokiaReadResponse result) {
				System.out.println("Result is " + result.getStatus());
				System.out.println("Result is " + result.getError());
				
				System.out.println("Value is " +  new JSONObject(result.getValue()).toString());
				Integer memoryMax = result.getValueProertyInt("max");
				System.out.println("Max memory is " + memoryMax);
			}
		});
		
	}
}