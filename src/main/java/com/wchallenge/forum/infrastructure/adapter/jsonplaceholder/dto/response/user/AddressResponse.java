package com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.dto.response.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddressResponse {

	private String street;
	private String suite;
	private String city;
	private String zipcode;
	private GeolocationResponse geo;
}
