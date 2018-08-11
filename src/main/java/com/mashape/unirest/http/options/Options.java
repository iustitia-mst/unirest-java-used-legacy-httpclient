package com.mashape.unirest.http.options;

import java.util.HashMap;

import java.util.Map;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
//import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
//import org.apache.http.impl.nio.client.HttpAsyncClients;
//import org.apache.http.nio.client.HttpAsyncClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class Options {

	public static final long CONNECTION_TIMEOUT = 10000;
	private static final long SOCKET_TIMEOUT = 60000;
	
	private static Map<Option, Object> options = new HashMap<Option, Object>();
	
	public static void setOption(Option option, Object value) {
		options.put(option, value);
	}
	
	public static Object getOption(Option option) {
		return options.get(option);
	}

	static {
		refresh();
	}
	
	public static void refresh() {
		// Load timeouts
		Object connectionTimeout = Options.getOption(Option.CONNECTION_TIMEOUT);
		if (connectionTimeout == null) connectionTimeout = CONNECTION_TIMEOUT;
		Object socketTimeout = Options.getOption(Option.SOCKET_TIMEOUT);
		if (socketTimeout == null) socketTimeout = SOCKET_TIMEOUT;
		
		HttpClient httpClient = new DefaultHttpClient();
	  HttpParams params = httpClient.getParams();
	  HttpConnectionParams.setConnectionTimeout(params, ((Long)connectionTimeout).intValue());
	  HttpConnectionParams.setSoTimeout(params, ((Long)socketTimeout).intValue());
		
		// Create clients
		setOption(Option.HTTPCLIENT, httpClient);
		
		// CloseableHttpAsyncClient asyncClient = HttpAsyncClients.createDefault();
	  // HttpParams params2 = asyncClient.getParams();
	  // HttpConnectionParams.setConnectionTimeout(params2, ((Long)connectionTimeout).intValue());
	  // HttpConnectionParams.setSoTimeout(params2, ((Long)socketTimeout).intValue());
		
		// setOption(Option.ASYNCHTTPCLIENT, asyncClient);
	}
	
}
