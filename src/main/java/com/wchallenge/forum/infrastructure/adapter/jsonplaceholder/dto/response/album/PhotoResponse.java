package com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.dto.response.album;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PhotoResponse {

	private int albumId;
	private int id;
	private String title;
	private String url;
	private String thumbnailUrl;
}
