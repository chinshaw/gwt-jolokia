package com.google.gwt.jolokia.jconsole;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class JolokiaConsole extends Composite {
	
	

	interface Binder extends UiBinder<Widget, JolokiaConsole> {}
	
	@UiField(provided=true)
	CellTree cellTree;
	
	
	public JolokiaConsole() {
		initWidget(GWT.<Binder>create(Binder.class).createAndBindUi(this));
	}
	
	
	
}
