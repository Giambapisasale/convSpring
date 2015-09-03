package com.xmlconverter.jaxb;

import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import com.thoughtworks.xstream.core.util.ThreadSafeSimpleDateFormat;

/**
 * used only for jaxb type setting on conversion
 * 
 * @author giambattista
 *
 */
public class DateAdapter {

	private static final TimeZone TIME_ZONE = TimeZone.getTimeZone("Europe/Rome");
	/**
	 * TODO limite dell'adapter, non e' possibile impostare questa
	 * configurazione nel file di configurazione di spring batch
	 */
	private static String format = "ddMMyy";
	private static ThreadSafeSimpleDateFormat df = new ThreadSafeSimpleDateFormat(format, TIME_ZONE, Locale.ITALIAN, 1,
			20, false);

	public static Date parseDate(String s) {
		return null;
	}

	/**
	 * used only from a jaxb2Marshaller
	 * 
	 * @param dt
	 * @return
	 */
	public static String printDate(Date dt) {
		return df.format(dt);
	}
}