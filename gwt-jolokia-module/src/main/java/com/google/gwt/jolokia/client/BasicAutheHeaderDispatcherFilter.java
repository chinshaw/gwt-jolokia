package com.google.gwt.jolokia.client;

import java.io.UnsupportedEncodingException;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.dispatcher.DispatcherFilter;

import com.google.gwt.http.client.RequestBuilder;
import com.googlecode.gwt.crypto.bouncycastle.util.encoders.Base64;

final class BasicAuthHeaderDispatcherFilter implements DispatcherFilter {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    public boolean filter(Method method, RequestBuilder builder) {
        try {

        	// Create the bacic, base64 encoded header value.
            String basicAuthHeaderValue = createBasicAuthHeaderValue(
                    UserCredentials.INSTANCE.getUserName(),
                    UserCredentials.INSTANCE.getPassword());
            
            builder.setHeader(AUTHORIZATION_HEADER, basicAuthHeaderValue);
            
            System.out.println("Authorization header is " + basicAuthHeaderValue);
        } catch (UnsupportedEncodingException e) {
            return false;
        }
        return true;
    }

    private String createBasicAuthHeaderValue(String userName, String password)
            throws UnsupportedEncodingException {
        String credentials = userName + ":" + password;
        String encodedCredentials = new String(Base64.encode(credentials
                .getBytes()), "UTF-8");

        return "Basic " + encodedCredentials;
    }


}

