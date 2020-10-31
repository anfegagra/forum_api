package com.wchallenge.forum.infrastructure.controller;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.wchallenge.forum.application.service.UserAppService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class UserControllerTest {

	private static MockMvc mockMvc;

	@BeforeAll
	static void setUp() {
		UserAppService userAppService = mock(UserAppService.class);
		UserController userController = new UserController(userAppService);

		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

	@Test
	void findAll() throws Exception {

		// Act - Assert
		mockMvc.perform(get("/api/v1/users"))
			.andExpect(status().isOk());
	}

}