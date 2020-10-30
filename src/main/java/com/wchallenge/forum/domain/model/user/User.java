package com.wchallenge.forum.domain.model.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {

	private int id;
	private String name;
	private String username;
	private String email;
	private Address address;
	private String phone;
	private String website;
	private Company company;
}
