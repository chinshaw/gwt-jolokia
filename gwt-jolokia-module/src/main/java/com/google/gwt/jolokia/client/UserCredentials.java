package com.google.gwt.jolokia.client;

public enum UserCredentials {

	INSTANCE("anonymous", null);

	private String userName;
	private String password;

	private UserCredentials(String userName, String password) {
		this.setUserName(userName);
		this.setPassword(password);
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
