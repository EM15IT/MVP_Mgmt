package com.MVP.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDetailsRequest {

	private Long userId;
	private String hotelId;
	private Integer noOfRooms;
	private Date checkInDate;
	private Date checkOutDate;
	private Double bookingPrice;
	private List<RoomDetailsDto> roomList;

}