package com.google.gwt.jolokia.jconsole;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.view.client.TreeViewModel;

public class MBeanTreeModel implements TreeViewModel {

	@Override
	public <T> NodeInfo<?> getNodeInfo(T value) {
		if (value == null) {
			return null;
		}
		JSONValue treeObject = (JSONValue) value;
		JSONObject jso = treeObject.isObject();
		
		for (String key : jso.keySet()) {
			
		}
		
		return null;
		
	}

	@Override
	public boolean isLeaf(Object value) {
		JSONObject obj = (JSONObject) value;
		return true;
	}

}
