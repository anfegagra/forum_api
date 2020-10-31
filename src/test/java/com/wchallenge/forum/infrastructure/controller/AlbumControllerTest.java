package com.wchallenge.forum.infrastructure.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.wchallenge.forum.application.dto.album.AlbumDto;
import com.wchallenge.forum.application.dto.album.PhotoDto;
import com.wchallenge.forum.application.service.AlbumAppService;
import java.util.Collections;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class AlbumControllerTest {

	private static AlbumAppService albumAppService;

	private static MockMvc mockMvc;

	@BeforeAll
	static void setUp() {
		albumAppService = mock(AlbumAppService.class);
		AlbumController albumController = new AlbumController(albumAppService);

		mockMvc = MockMvcBuilders.standaloneSetup(albumController).build();
	}

	@Test
	void findAllPhotos() throws Exception {

		// Arrange
		PhotoDto photoDto = PhotoDto.builder().id(1).title("test").build();

		when(albumAppService.findAllPhotos()).thenReturn(Collections.singletonList(photoDto));

		// Act - Assert
		mockMvc.perform(get("/api/v1/albums/photos"))
			.andDo(print()).andExpect(status().isOk())
			.andExpect(jsonPath("data[0].id").value(photoDto.getId()))
			.andExpect(jsonPath("data[0].title").value(photoDto.getTitle()));
	}

	@Test
	void findAll() throws Exception {

		// Arrange
		AlbumDto albumDto = AlbumDto.builder().userId(1).id(1).title("test").build();

		when(albumAppService.findAll(any())).thenReturn(Collections.singletonList(albumDto));

		// Act - Assert
		mockMvc.perform(get("/api/v1/albums"))
			.andDo(print()).andExpect(status().isOk())
			.andExpect(jsonPath("data[0].userId").value(albumDto.getUserId()))
			.andExpect(jsonPath("data[0].id").value(albumDto.getId()))
			.andExpect(jsonPath("data[0].title").value(albumDto.getTitle()));
	}

	@Test
	void findAlbumsByUserId() throws Exception {

		// Arrange
		AlbumDto albumDto = AlbumDto.builder().userId(1).id(1).title("test").build();

		when(albumAppService.findAll(any())).thenReturn(Collections.singletonList(albumDto));

		// Act - Assert
		mockMvc.perform(get("/api/v1/albums").param("userId", "1"))
			.andDo(print()).andExpect(status().isOk())
			.andExpect(jsonPath("data[0].userId").value(albumDto.getUserId()))
			.andExpect(jsonPath("data[0].id").value(albumDto.getId()))
			.andExpect(jsonPath("data[0].title").value(albumDto.getTitle()));
	}

}