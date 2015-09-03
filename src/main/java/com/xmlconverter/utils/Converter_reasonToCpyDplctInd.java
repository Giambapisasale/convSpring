package com.xmlconverter.utils;

import org.dozer.ConfigurableCustomConverter;

import com.xmlconverter.schema.CBICopyDuplicate1Code;

public class Converter_reasonToCpyDplctInd implements ConfigurableCustomConverter {

	String parameter;

	@Override
	public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass,
			Class<?> sourceClass) {

		// file: EC_RH_mappingXml_v1.xslx - ID 2.4 - +<CpyDplctInd>
		// In caso di trasmissione di recupero e' presente e assume il valore
		// DUPL
		// TODO non e' chiara la transcodifica

		if (sourceFieldValue != null && ((String) sourceFieldValue).trim().equals("93011")) {
			return CBICopyDuplicate1Code.DUPL;
		} else {
			return null;
		}

	}

	@Override
	public void setParameter(String parameter) {
		this.parameter = parameter;

	}

}