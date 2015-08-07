package com.trim.jaxb;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;
import com.thoughtworks.xstream.core.util.ThreadSafeSimpleDateFormat;

public class DateConverter extends AbstractSingleValueConverter {

	private static final TimeZone TIME_ZONE = TimeZone.getTimeZone("Europe/Rome");
	private ThreadSafeSimpleDateFormat df;

	public DateConverter(String format) {
		df = new ThreadSafeSimpleDateFormat(format, TIME_ZONE, Locale.ITALIAN, 1, 20, false);
	}

	@Override
	public boolean canConvert(Class type) {
		return type.equals(Date.class);
	}

	@Override
	public Object fromString(String str) {

		try {
			return df.parse(str);
		} catch (ParseException e) {
			// log.error
		}
		throw new ConversionException("Cannot parse date " + str);
	}

	@Override
	public String toString(Object obj) {
		final Date date = (Date) obj;
		return df.format(date);
	}

}
