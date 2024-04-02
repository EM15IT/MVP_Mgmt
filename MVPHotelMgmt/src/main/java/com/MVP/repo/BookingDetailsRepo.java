package com.MVP.repo;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.MVP.domain.BookingDetails;

public interface BookingDetailsRepo extends JpaRepository<BookingDetails, Long> {

	List<BookingDetails> findByUserId(Long userId);

	List<BookingDetails> findByUserId(@Valid Long userId, Pageable page);

}