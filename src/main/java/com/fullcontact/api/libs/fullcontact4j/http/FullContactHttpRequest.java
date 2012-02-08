package com.fullcontact.api.libs.fullcontact4j.http;

import com.fullcontact.api.libs.fullcontact4j.FullContactException;
import com.fullcontact.api.libs.fullcontact4j.config.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.MalformedURLException;
import java.net.URL;

public class FullContactHttpRequest {
	public static String sendRequest(String requestString)
			throws FullContactException {
		StringBuffer buffer = new StringBuffer();

		try {
			String apiUrl = Constants.API_URL + requestString;
			URL url = new URL(apiUrl);

			BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream(), Constants.UTF_8_CHARSET));
			String str;

			while ((str = in.readLine()) != null) {
				buffer.append(str);
			}

			in.close();
		} catch (MalformedURLException e) {
			throw new FullContactException(e.getMessage());
		} catch (IOException e) {
			throw new FullContactException(e.getMessage());
		}

		return buffer.toString();
	}
}
