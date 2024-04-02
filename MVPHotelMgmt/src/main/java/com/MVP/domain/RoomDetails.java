package com.MVP.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(RoomDetailsPK.class)
@Table(name = "room_details")
public class RoomDetails {

	@Id
	@Column(name ="booking_id")
	private Long bookingId;

	@Id
	@Column(name ="room_no")
	private String roomNo;

	@Column(name ="room_type")
	private String roomType;
	
	@Column(name ="check_in_date")
	private Date checkInDate;
	
	@Column(name ="check_out_date")
	private Date checkOutDate; 
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name ="creation_date")
	private Date creationDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name ="modified_date")
	private Date modifiedDate;

}