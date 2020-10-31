package com.wchallenge.forum.application.dto.album;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhotoDto {

	private int albumId;
	private int id;
	private String title;
	private String url;
	private String thumbnailUrl;
}
