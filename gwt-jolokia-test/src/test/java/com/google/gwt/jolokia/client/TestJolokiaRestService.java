package com.google.gwt.jolokia.client;

import java.util.logging.Logger;

import org.junit.Test;

import com.google.gwt.http.client.RequestException;
import com.google.gwt.jolokia.client.rest.JolokiaClient;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class TestJolokiaRestService extends GWTTestCase {

	public static final Logger logger = Logger.getLogger(TestJolokiaRestService.class.getName());
	
	@Override
	public String getModuleName() {
		return "com.google.gwt.jolokia.JolokiaTest";
	}

	@Test
	public void testGetMemory() throws RequestException {
		final JolokiaClient client = new JolokiaClient("/com.google.gwt.jolokia.JolokiaTest.JUnit/api/jolokia",
				"admin", "admin");

		client.getAttribute("java.lang:type=Memory", "HeapMemoryUsage", null, null,
				new AsyncCallback<JolokiaReadResponse>() {

					@Override
					public void onFailure(Throwable caught) {
						fail(caught.getMessage());
					}

					@Override
					public void onSuccess(JolokiaReadResponse result) {
						assertTrue(result.getStatus() == 200);
						System.out.println("Value is " + new JSONObject(result.getValue()).toString());
						Integer memoryMax = result.getValuePropertyInt("max");
						System.out.println("Max memory is " + memoryMax);
					}
				});
	}

	@Test
	public void testMultiRequest() throws RequestException {
		final JolokiaClient client = new JolokiaClient("/com.google.gwt.jolokia.JolokiaTest.JUnit/api/jolokia",
				"admin", "admin");

		JolokiaReadRequest heapRequest = JolokiaReadRequest.create();
		heapRequest.setMbean("java.lang:type=Memory");
		heapRequest.setAttribute("HeapMemoryUsage");

		JolokiaReadRequest queueRequest = JolokiaReadRequest.create();
		queueRequest
				.setMbean("org.apache.activemq:type=Broker,brokerName=localhost,destinationType=Queue,destinationName=com.stomp.test");

		JolokiaMultiRequest multiRequest = JolokiaMultiRequest.create();
		multiRequest.push(heapRequest);
		multiRequest.push(queueRequest);

		client.send(multiRequest, new AsyncCallback<JolokiaMultiResponse>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(JolokiaMultiResponse result) {
				System.out.println("result is " + new JSONObject(result));
				assertTrue(result.length() > 0);
				for (int i = 0; i < result.length(); i++) {
					logger.info("Response " + i + " "  + new JSONObject(result.get(i)).toString());	
				}
			}

		});
	}
}