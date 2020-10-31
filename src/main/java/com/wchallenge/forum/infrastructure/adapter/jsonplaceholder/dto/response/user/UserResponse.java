package com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.dto.response.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse {

	private int id;
	private String name;
	private String username;
	private String email;
	private AddressResponse address;
	private String phone;
	private String website;
	private CompanyResponse company;
}
