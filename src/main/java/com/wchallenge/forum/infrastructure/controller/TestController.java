package com.wchallenge.forum.infrastructure.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

	@Value("${test.non-updatable-property}")
	private String nonUpdatableProperty;

	@GetMapping("/value")
	public String getValue(@Value("${test.updatable-property}") String updatableProperty) {
		return "Non updatable property: " + nonUpdatableProperty + " \nUpdatable property: "
			+ updatableProperty;
	}
}
