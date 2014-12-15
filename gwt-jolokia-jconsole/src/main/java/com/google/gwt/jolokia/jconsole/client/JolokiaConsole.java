package com.google.gwt.jolokia.jconsole.client;

import java.util.logging.Logger;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class JolokiaConsole extends Composite {

	private static final Logger logger = Logger.getLogger(JolokiaConsole.class.getName());
	
	interface Binder extends UiBinder<Widget, JolokiaConsole> {
	}

	@UiField(provided = true)
	CellTree mbeans;

	public JolokiaConsole(final MBeanTreeModel model, final JSONObject jso) {
		mbeans = new CellTree(model, jso);
		logger.info("INitializing view");
		initWidget(GWT.<Binder> create(Binder.class).createAndBindUi(this));
	}

}
