package com.wchallenge.forum.domain.model.user;

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
public class Address {

	private String street;
	private String suite;
	private String city;
	private String zipcode;
	private Geolocation geo;
}
