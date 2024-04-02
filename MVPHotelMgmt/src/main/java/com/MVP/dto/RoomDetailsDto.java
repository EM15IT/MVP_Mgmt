package com.MVP.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDetailsDto {

	
	private Long bookingId;
	
	private String roomNo;
	
	private String roomType;
	
	private Date checkInDate;
	private Date checkOutDate; 
}