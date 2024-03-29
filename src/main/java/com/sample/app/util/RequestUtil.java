package com.sample.app.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import com.sample.app.model.RequestInfo;

public class RequestUtil {
	public static RequestInfo getRequestInfo(HttpServletRequest request) throws IOException {
		RequestInfo requestInfo = new RequestInfo();

		// a. Request headers
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			requestInfo.getHeaders().put(headerName, request.getHeader(headerName));
		}

		// b. Request parameters
		Map<String, String[]> parameters = request.getParameterMap();
		requestInfo.getParameters().putAll(parameters);
		// c. Request attributes
		Enumeration<String> attributeNames = request.getAttributeNames();
		while (attributeNames.hasMoreElements()) {

			String attributeName = attributeNames.nextElement();

			if (attributeName.startsWith("test")) {
				requestInfo.getAttributes().put(attributeName, request.getAttribute(attributeName));
			}

		}

		InputStream is = request.getInputStream();
		String payload = toString(is);

		requestInfo.setPayload(payload);
		return requestInfo;
	}

	private static String toString(InputStream is) throws IOException {
		if (is == null) {
			return "";
		}

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
			return reader.lines().collect(Collectors.joining("\n"));
		} catch (IOException e) {
			throw e;
		}
	}

}
