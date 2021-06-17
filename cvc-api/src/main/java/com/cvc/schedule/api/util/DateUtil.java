package com.cvc.schedule.api.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {

	public static LocalDate transformStringToLocalDate(String date) {
		if (date == null) {
			return null;
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
		LocalDate dateTime = LocalDate.parse(date, formatter);
		return dateTime;
	}
}
