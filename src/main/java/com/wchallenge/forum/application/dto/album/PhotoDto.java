package com.wchallenge.forum.application.dto.album;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PhotoDto {

	private int albumId;
	private int id;
	private String title;
	private String url;
	private String thumbnailUrl;
}
