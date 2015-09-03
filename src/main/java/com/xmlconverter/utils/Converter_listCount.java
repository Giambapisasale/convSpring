package com.xmlconverter.utils;

import java.util.List;

import org.dozer.DozerConverter;

@SuppressWarnings("rawtypes")
public class Converter_listCount extends DozerConverter<String, List> {

	public Converter_listCount() {
		super(String.class, List.class);
	}

	@Override
	public List convertTo(String source, List destination) {
		return null;
	}

	@Override
	public String convertFrom(List source, String destination) {
		return source.size() + "";
	}

}