package com.utec.voting.util;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONObject;

public class ClientWebService {
	public String clienteWS(String uri, JSONObject object,String method) throws IOException {
		String resp = null;
		HttpURLConnection connection = null;
		URL url = new URL(uri);
		connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod(method);
		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setUseCaches(false);
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("Accept", "application/json");
		
		OutputStream output = new BufferedOutputStream(connection.getOutputStream());
		output.write(object.toString().getBytes());
		output.flush();
		InputStream xml = connection.getInputStream();

		@SuppressWarnings("resource")
		Scanner s = new Scanner(xml).useDelimiter("\\A");
		resp = s.hasNext() ? s.next() : "";
		return resp;
	}
}
