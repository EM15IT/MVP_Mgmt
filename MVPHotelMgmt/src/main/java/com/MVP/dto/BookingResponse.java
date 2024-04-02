package com.MVP.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponse {
	
	private Long bookingId;
	
	private HotelDetailsDto hotelDetails;
	
	private List<RoomDetailsDto> roomList;

}