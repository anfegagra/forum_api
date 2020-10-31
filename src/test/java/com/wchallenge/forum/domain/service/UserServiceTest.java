package com.wchallenge.forum.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.wchallenge.forum.domain.model.user.Address;
import com.wchallenge.forum.domain.model.user.Geolocation;
import com.wchallenge.forum.domain.model.user.User;
import com.wchallenge.forum.domain.port.UserPort;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@Mock
	private UserPort userPort;

	@InjectMocks
	private UserService userService;

	@Test
	void findAll() {

		// Arrange
		User userDto = User.builder().id(1).name("Andrés Felipe").username("Pipe")
			.email("pipe@hotmail.com").address(
				Address.builder().street("Alcalá").suite("Apto. 1").city("Envigado")
					.zipcode("055422").geo(
					Geolocation.builder().lat("-37.3159").lng("81.1496").build()).build())
			.build();

		when(userPort.findAll()).thenReturn(Collections.singletonList(userDto));

		// Act
		List<User> userDtoList = userService.findAll();

		// Assert
		assertThat(userDtoList).usingRecursiveFieldByFieldElementComparator()
			.isEqualTo(Collections.singletonList(userDto));
	}

}