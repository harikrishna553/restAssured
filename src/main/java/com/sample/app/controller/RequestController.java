package com.sample.app.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sample.app.model.RequestInfo;
import com.sample.app.util.RequestUtil;

@RestController
@RequestMapping("api/v1/replay-request")
public class RequestController {

	@Autowired
	private HttpServletRequest httpServletRequest;

	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
	public RequestInfo handleRequests() throws IOException {
		return RequestUtil.getRequestInfo(httpServletRequest);
	}

}
