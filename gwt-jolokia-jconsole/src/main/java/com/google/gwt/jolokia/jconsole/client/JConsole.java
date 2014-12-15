package com.google.gwt.jolokia.jconsole.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.jolokia.client.JolokiaListResponse;
import com.google.gwt.jolokia.client.rest.JolokiaClient;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.view.client.TreeViewModel;

public class JConsole implements EntryPoint {

	private static final Logger logger = Logger.getLogger(JConsole.class.getName());
	
	private static final JolokiaClient client = new JolokiaClient("http://127.0.0.1:8888/amq/jmx", "admin", "admin");
	
	@Override
	public void onModuleLoad() {
		final MBeanTreeModel model = new MBeanTreeModel();
		try {
			client.listMbeans(new AsyncCallback<JolokiaListResponse>() {
				
				@Override
				public void onSuccess(JolokiaListResponse result) {
					
					JSONObject jso = new JSONObject(result.getValue());
					RootLayoutPanel.get().add(new JolokiaConsole(model, jso));
				}
				
				@Override
				public void onFailure(Throwable caught) {
					logger.log(Level.SEVERE, "Unable to fetch mbeans", caught);
					
				}
			});
		} catch (RequestException e) {
			logger.log(Level.SEVERE, "Failed to make request. Cause: ", e);
		}
		
		
	}	
}
