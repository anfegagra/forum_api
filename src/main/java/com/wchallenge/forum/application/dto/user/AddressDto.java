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
public class AddressDto {

	private String street;
	private String suite;
	private String city;
	private String zipcode;
	private GeolocationDto geo;
}
