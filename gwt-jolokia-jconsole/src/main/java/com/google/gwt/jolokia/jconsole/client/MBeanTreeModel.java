package com.google.gwt.jolokia.jconsole.client;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.TreeViewModel;

public class MBeanTreeModel implements TreeViewModel {

	private static class JSOWrapper {

		private String key;

		private JSONValue value;

		public JSOWrapper(String key, JSONValue value) {
			this.key = key;
			this.value = value;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public JSONValue getValue() {
			return value;
		}

		public void setValue(JSONValue value) {
			this.value = value;
		}

	}

	private static final Logger logger = Logger.getLogger(MBeanTreeModel.class.getName());

	private static class JSOWrapperCell extends AbstractCell<JSOWrapper> {

		@Override
		public void render(com.google.gwt.cell.client.Cell.Context context, JSOWrapper value, SafeHtmlBuilder sb) {
			sb.appendHtmlConstant(value.key);
		}
	}

	@Override
	public <T> NodeInfo<?> getNodeInfo(T value) {
		logger.info("GETTING VALUE");
		if (value == null) {
			logger.info("Value is null");
		}

		logger.info("Value is " + value);

		JSOWrapper wrapper = null;

		if (value instanceof JSONValue) {
			wrapper = new JSOWrapper("ROOT", ((JSONValue) value).isObject());
		} else {
			wrapper = (JSOWrapper) value;
		}

		JSONObject jso;
		if ((jso = wrapper.getValue().isObject()) != null) {
			List<JSOWrapper> dataProvider = new ArrayList<JSOWrapper>();
			for (String key : jso.keySet()) {
				JSOWrapper childWrapper = new JSOWrapper(key, wrapper.getValue().isObject().get(key));
				dataProvider.add(childWrapper);
			}
			return new DefaultNodeInfo<JSOWrapper>(new ListDataProvider<JSOWrapper>(dataProvider), new JSOWrapperCell());
		}
		
		JSONArray jsa;
		if (( jsa = wrapper.getValue().isArray()) != null) {
			
		}
		
		throw new IllegalArgumentException("Unknown type " + wrapper.getValue());
	}

	@Override
	public boolean isLeaf(Object value) {
		if (value instanceof JSONValue) {
			return false;
		}
		JSOWrapper wrapper = (JSOWrapper) value;
		if (wrapper.getValue().isString() != null) {
			return true;
		}
		return false;
	}

}
