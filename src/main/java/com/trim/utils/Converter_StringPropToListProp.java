package com.trim.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.dozer.ConfigurableCustomConverter;

import com.trim.jaxb.DateConverter;

public class Converter_StringPropToListProp implements ConfigurableCustomConverter {

	String parameter;

	@Override
	public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass,
			Class<?> sourceClass) {

		
		@SuppressWarnings("unchecked")
		List<Object> dest = (List<Object>) existingDestinationFieldValue;
		for (Object object : dest) {
			try {
				PropertyUtils.setSimpleProperty(object, this.parameter, sourceFieldValue);
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