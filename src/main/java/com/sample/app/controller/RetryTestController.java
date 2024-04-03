package com.sample.app.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/retry-test")
public class RetryTestController {
	private static final AtomicInteger counter = new AtomicInteger(0);
	
	/**
	 * Every thrid call will succeed
	 * @return
	 * @throws Exception
	 */
	@GetMapping
	public ResponseEntity<Map<String, Object>> test() throws Exception{
		int tempCount = counter.incrementAndGet();
		if(tempCount % 3 == 0) {
			Map<String, Object> map = new HashMap<>();
			map.put("status", "200");
			return ResponseEntity.ok(map);
		}
		
		return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
		
	}
}
