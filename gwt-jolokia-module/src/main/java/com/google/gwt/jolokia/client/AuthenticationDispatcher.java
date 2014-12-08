package com.google.gwt.jolokia.client;

import org.fusesource.restygwt.client.dispatcher.DefaultFilterawareDispatcher;

public class AuthenticationDispatcher extends DefaultFilterawareDispatcher {

	public AuthenticationDispatcher() {
		addFilter(new BasicAuthHeaderDispatcherFilter());
	}
}
