package com.MVP.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MVP.domain.RoomDetails;
import com.MVP.domain.RoomDetailsPK;

public interface RoomDetailsRepo extends JpaRepository<RoomDetails, RoomDetailsPK> {

	List<RoomDetails> findByBookingId(Long bookingId);

}