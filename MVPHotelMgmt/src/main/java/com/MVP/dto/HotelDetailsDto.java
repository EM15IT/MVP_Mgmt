package com.MVP.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelDetailsDto {

	private String hotelName;

	private String countryName;

	private String cityName;

	private Long zipCode;

}