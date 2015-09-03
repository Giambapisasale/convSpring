package com.xmlconverter.utils;

import org.dozer.ConfigurableCustomConverter;

/**
 * set a fixed string value, use parameter
 * 
 * @author giambattista
 *
 */
@SuppressWarnings("rawtypes")
public class Converter_fixedValue implements ConfigurableCustomConverter {

	String parameter;

	@Override
	public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass,
			Class<?> sourceClass) {

		return this.parameter;

	}

	@Override
	public void setParameter(String parameter) {
		this.parameter = parameter;

	}

}