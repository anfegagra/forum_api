package com.wchallenge.forum.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.wchallenge.forum.application.dto.user.AddressDto;
import com.wchallenge.forum.application.dto.user.GeolocationDto;
import com.wchallenge.forum.application.dto.user.UserDto;
import com.wchallenge.forum.application.mapper.UserAppMapper;
import com.wchallenge.forum.domain.service.UserService;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserAppServiceTest {

	@Mock
	private UserService userService;

	@Mock
	private UserAppMapper userAppMapper;

	@InjectMocks
	private UserAppService userAppService;

	@Test
	void findAll() {

		// Arrange
		UserDto userDto = UserDto.builder().id(1).name("Andrés Felipe").username("Pipe")
			.email("pipe@hotmail.com").address(
				AddressDto.builder().street("Alcalá").suite("Apto. 1").city("Envigado")
					.zipcode("055422").geo(
					GeolocationDto.builder().lat("-37.3159").lng("81.1496").build()).build())
			.build();

		when(userAppService.findAll()).thenReturn(Collections.singletonList(userDto));

		// Act
		List<UserDto> userDtoList = userAppService.findAll();

		// Assert
		assertThat(userDtoList).usingRecursiveFieldByFieldElementComparator()
			.isEqualTo(Collections.singletonList(userDto));
	}

}