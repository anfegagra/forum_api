package com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.dto.response.post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentResponse {

	private int postId;
	private int id;
	private String name;
	private String email;
	private String body;
}
