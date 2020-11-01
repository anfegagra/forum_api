package com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.dto.response.post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostResponse {

	private int userId;
	private int id;
	private String title;
	private String body;
}
