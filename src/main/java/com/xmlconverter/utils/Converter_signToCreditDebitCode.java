package com.xmlconverter.utils;

import org.dozer.ConfigurableCustomConverter;

import com.xmlconverter.schema.CBICopyDuplicate1Code;
import com.xmlconverter.schema.CreditDebitCode;

public class Converter_signToCreditDebitCode implements ConfigurableCustomConverter {

	String parameter;

	@Override
	public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass,
			Class<?> sourceClass) {

		// file: EC_RH_mappingXml_v1.xslx - ID 2.4 - +<CpyDplctInd>
		// In caso di trasmissione di recupero e' presente e assume il valore
		// DUPL

		if (sourceFieldValue != null && ((String) sourceFieldValue).trim().equalsIgnoreCase("D")) {
			return CreditDebitCode.DBIT;
		} else if (sourceFieldValue != null && ((String) sourceFieldValue).trim().equalsIgnoreCase("C")) {
			return CreditDebitCode.CRDT;
		} else {
			return null;
		}

	}

	@Override
	public void setParameter(String parameter) {
		this.parameter = parameter;

	}

}