package com.MVP.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class CommonUtil {
	
	private static String indiaZone = "Asia/Kolkata";

	public static Timestamp getCurrentISTTime() {
		return Timestamp.valueOf(LocalDateTime.now(ZoneId.of(indiaZone)));
	}

}