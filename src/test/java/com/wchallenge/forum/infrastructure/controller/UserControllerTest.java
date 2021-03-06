package com.wchallenge.forum.infrastructure.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.wchallenge.forum.application.dto.user.UserDto;
import com.wchallenge.forum.application.service.UserAppService;
import java.util.Collections;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class UserControllerTest {

	private static UserAppService userAppService;

	private static MockMvc mockMvc;

	@BeforeAll
	static void setUp() {
		userAppService = mock(UserAppService.class);
		UserController userController = new UserController(userAppService);

		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

	@Test
	void findAll() throws Exception {

		// Arrange
		UserDto userDto = UserDto.builder().id(1).username("pipe").build();

		when(userAppService.findAll()).thenReturn(Collections.singletonList(userDto));

		// Act - Assert
		mockMvc.perform(get("/api/v1/users"))
			.andDo(print()).andExpect(status().isOk())
			.andExpect(jsonPath("data[0].id").value(userDto.getId()))
			.andExpect(jsonPath("data[0].username").value(userDto.getUsername()));
	}

}