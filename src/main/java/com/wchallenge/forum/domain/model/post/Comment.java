package com.wchallenge.forum.domain.model.post;

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
public class Comment {

	private int postId;
	private int id;
	private String name;
	private String email;
	private String body;
}
