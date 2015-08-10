package com.trim.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.dozer.ConfigurableCustomConverter;

import com.trim.jaxb.DateConverter;

public class Converter_propToList implements ConfigurableCustomConverter {

	String parameter;

	@Override
	public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass,
			Class<?> sourceClass) {

		DateConverter conv = new DateConverter("ddMMyy");
		Date date = (Date) conv.fromString((String) sourceFieldValue);
		// TODO generalizzare oppure specializzare il caso per la data

		@SuppressWarnings("unchecked")
		List<Object> dest = (List<Object>) existingDestinationFieldValue;
		for (Object object : dest) {
			try {
				Date d2 = new Date(date.getTime());
				PropertyUtils.setSimpleProperty(object, this.parameter, d2);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return existingDestinationFieldValue;
	}

	@Override
	public void setParameter(String parameter) {
		this.parameter = parameter;

	}
}