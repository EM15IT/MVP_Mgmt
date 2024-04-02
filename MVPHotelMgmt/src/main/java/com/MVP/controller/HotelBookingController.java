package com.MVP.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.MVP.dto.BookingDetailsRequest;
import com.MVP.dto.BookingResponse;
import com.MVP.service.HotelBookingService;

@RestController
@RequestMapping("/mvp/hotel")
public class HotelBookingController {

	@Autowired
	private HotelBookingService hotelBookingService;

	@PostMapping("/saveBookingDetails")
	public BookingResponse saveBookingDetails(@RequestBody BookingDetailsRequest bookingDetailsDto)
			throws Exception {
		BookingResponse response = hotelBookingService.saveBookingDetails(bookingDetailsDto);
		return response;
	}

	@PostMapping("/getBookingDetails")
	public List<BookingResponse> getBookingDetails(@RequestParam(name = "userId", required = true) Long userId,
			@RequestParam(name = "pageNo", required = false) Integer pageNo,
			@RequestParam(name = "itemPerPage", required = false) Integer itemPerPage) throws Exception {
		List<BookingResponse> response = hotelBookingService.getBookingDetails(userId, pageNo, itemPerPage);
		return response;
	}

}