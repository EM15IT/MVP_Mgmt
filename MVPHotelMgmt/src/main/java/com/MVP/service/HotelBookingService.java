package com.MVP.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.MVP.domain.BookingDetails;
import com.MVP.domain.HotelDetailsMaster;
import com.MVP.domain.RoomDetails;
import com.MVP.dto.BookingDetailsRequest;
import com.MVP.dto.BookingResponse;
import com.MVP.dto.HotelDetailsDto;
import com.MVP.dto.RoomDetailsDto;
import com.MVP.repo.BookingDetailsRepo;
import com.MVP.repo.HotelDetailsMasterRepo;
import com.MVP.repo.RoomDetailsRepo;
import com.MVP.util.CommonUtil;
import com.MVP.util.MessageUtil;

@Service
public class HotelBookingService {

	@Autowired
	private BookingDetailsRepo bookingDetailsRepo;

	@Autowired
	private RoomDetailsRepo roomDetailsRepo;

	@Autowired
	private HotelDetailsMasterRepo hotelDetailsMasterRepo;

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private MessageUtil messageUtil;

	@SuppressWarnings("null")
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
	public BookingResponse saveBookingDetails(@Valid BookingDetailsRequest bookingDetailsRequest) {

		BookingResponse reponse = new BookingResponse();
		BookingDetails bookingDetails = new BookingDetails();
		modelMapper.map(bookingDetailsRequest, bookingDetails);

		bookingDetails.setCreationDate(CommonUtil.getCurrentISTTime());
		bookingDetails.setModifiedDate(CommonUtil.getCurrentISTTime());
		BookingDetails savedDetails = bookingDetailsRepo.save(bookingDetails);
		List<RoomDetailsDto> saveRoomDetails = saveRoomDetails(bookingDetailsRequest.getRoomList(), savedDetails);
		modelMapper.map(savedDetails, reponse);
		reponse.setRoomList(saveRoomDetails);
		Optional<HotelDetailsMaster> hotelDetails = hotelDetailsMasterRepo.findById(savedDetails.getHotelId());
		if (!ObjectUtils.isEmpty(hotelDetails)) {
			HotelDetailsDto details = new HotelDetailsDto();
			modelMapper.map(hotelDetails, details);
			reponse.setHotelDetails(details);
		}

		return reponse;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<RoomDetailsDto> saveRoomDetails(List<RoomDetailsDto> roomList, BookingDetails savedDetails) {

		List<RoomDetailsDto> roomDetailsList = new ArrayList<RoomDetailsDto>();
		Iterator<RoomDetailsDto> iter = roomList.iterator();
		while (iter.hasNext()) {
			RoomDetailsDto roomDetailsDto = iter.next();
			RoomDetails roomDetails = new RoomDetails();
			modelMapper.map(roomDetailsDto, roomDetails);
			roomDetails.setBookingId(savedDetails.getBookingId());
			roomDetails.setCheckInDate(savedDetails.getCheckInDate());
			roomDetails.setCheckOutDate(savedDetails.getCheckOutDate());
			roomDetails.setModifiedDate(savedDetails.getModifiedDate());
			roomDetails.setCreationDate(savedDetails.getCreationDate());
			RoomDetails savedRoomDetails = roomDetailsRepo.save(roomDetails);
			RoomDetailsDto returnResponse = new RoomDetailsDto();
			modelMapper.map(savedRoomDetails, returnResponse);
			roomDetailsList.add(returnResponse);
		}
		return roomDetailsList;
	}

	public List<BookingResponse> getBookingDetails(@Valid Long userId, Integer pageNo, Integer itemPerPage)
			throws Exception {

		List<BookingResponse> allBookingDetails = new ArrayList<>();
		List<BookingDetails> bookingDetails = null;
		if (pageNo == null || itemPerPage == null) {
			bookingDetails = bookingDetailsRepo.findByUserId(userId);
		} else {
			Pageable page = PageRequest.of(pageNo, itemPerPage);
			bookingDetails = bookingDetailsRepo.findByUserId(userId, page);
		}
		if (CollectionUtils.isEmpty(bookingDetails)) {
			// we can use our own exception classes to create exception object here
			
			throw new Exception(messageUtil.getNoBooking());
		}
		Iterator<BookingDetails> iter = bookingDetails.iterator();
		while (iter.hasNext()) {
			BookingDetails booking = iter.next();
			BookingResponse response = new BookingResponse();
			HotelDetailsDto hotelDetails = new HotelDetailsDto();
			List<RoomDetailsDto> roomList = new ArrayList<>();
			modelMapper.map(booking, response);
			Optional<HotelDetailsMaster> hotel = hotelDetailsMasterRepo.findById(booking.getHotelId());
			//for the hotel details we have already registered hotel database
			modelMapper.map(hotel, hotelDetails);
			response.setHotelDetails(hotelDetails);
			List<RoomDetails> roomsByBookingId = roomDetailsRepo.findByBookingId(booking.getBookingId());
			if(!CollectionUtils.isEmpty(roomsByBookingId)) {
				for(RoomDetails rooms:roomsByBookingId) {
					RoomDetailsDto room = new RoomDetailsDto();
					modelMapper.map(rooms, room);
					roomList.add(room);
				}
			}
			response.setRoomList(roomList);
			allBookingDetails.add(response);
		}
		return allBookingDetails;
	}

}