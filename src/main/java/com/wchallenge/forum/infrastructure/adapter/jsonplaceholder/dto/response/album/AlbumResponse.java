package com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.dto.response.album;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AlbumResponse {

	private int userId;
	private int id;
	private String title;
}
