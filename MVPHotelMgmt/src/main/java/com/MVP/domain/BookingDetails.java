package com.MVP.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "booking_details")
public class BookingDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="booking_id")
	private Long bookingId;
	
	@Column(name ="user_id")
	private Long userId;
	
	@Column(name ="no_of_rooms")
	private Integer noOfRooms;
	
	@Column(name ="hotel_id")
	private String hotelId;
	
	@Column(name ="check_in_date")
	private Date checkInDate;
	
	@Column(name ="check_out_date")
	private Date checkOutDate; 
	
	@Column(name ="booking_price")
	private Double bookingPrice;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name ="creation_date")
	private Date creationDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name ="modified_date")
	private Date modifiedDate;

}