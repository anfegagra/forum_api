package com.wchallenge.forum.application.dto.user;

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
public class UserDto {

	private int id;
	private String name;
	private String username;
	private String email;
	private AddressDto address;
	private String phone;
	private String website;
	private CompanyDto company;
}
