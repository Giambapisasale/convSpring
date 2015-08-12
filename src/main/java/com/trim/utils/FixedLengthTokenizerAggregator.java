package com.trim.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.batch.item.file.transform.DefaultFieldSetFactory;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.batch.item.file.transform.FieldSetFactory;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;

public class FixedLengthTokenizerAggregator extends FixedLengthTokenizer {

	private FieldSetFactory fieldSetFactory = new DefaultFieldSetFactory();
	/**
	 * define an aggregation of field AFTER tokenize
	 * 
	 * i.e. iban => state_code, check_digit, abi, cab...
	 */
	private Map<String, String> aggregateFields;

	public Map<String, String> getAggregateFields() {
		return aggregateFields;
	}

	public void setAggregateFields(Map<String, String> aggregateFields) {
		this.aggregateFields = aggregateFields;
	}

	@Override
	public FieldSet tokenize(String line) {
		FieldSet tokens = super.tokenize(line);

		List<String> names_ext = new ArrayList<String>();
		names_ext.addAll(Arrays.asList(names));		
		names_ext.addAll(aggregateFields.keySet());
		
		String[] values = tokens.getValues();
				
		List<String> values_ext = new ArrayList<String>();
		values_ext.addAll(Arrays.asList(values));
		
		for (String key: aggregateFields.keySet()) {
			StringBuilder add_value = new StringBuilder();
			String[] to_aggregate = aggregateFields.get(key).split(",");
			for (String field : to_aggregate) {
				if(field!= null && !field.trim().isEmpty()) {
					add_value.append(tokens.readString(field.trim()));
				}
			}
			values_ext.add(add_value.toString());
		}

		tokens = fieldSetFactory.create(values_ext.toArray(new String[0]), names_ext.toArray(new String[0]));
		return tokens;
	}

}
