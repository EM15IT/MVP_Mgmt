package com.MVP.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hotel_details")
public class HotelDetailsMaster {
	
	@Id
	@Column(name ="hotel_id")
	private String hotelId;
	
	@Column(name ="hotel_name")
	private String hotelName;
	
	@Column(name ="country_name")
	private String countryName;
	
	@Column(name ="city_name")
	private String cityName;
	
	@Column(name ="zip_code")
	private Long zipCode;
	
	

}