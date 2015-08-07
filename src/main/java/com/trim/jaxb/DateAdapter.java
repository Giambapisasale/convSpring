package com.trim.jaxb;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.DatatypeConverter;

public class DateAdapter {
	private static SimpleDateFormat df = new SimpleDateFormat("ddMMyy");

	public static Date parseDate(String s) {
		try {
			return df.parse(s);
		} catch (ParseException e) {
//			log.error
		}
		return null;
	}

	public static String printDate(Date dt) {
		return df.format(dt);
	}
}