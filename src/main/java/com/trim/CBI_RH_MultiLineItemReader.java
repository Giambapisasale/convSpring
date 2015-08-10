package com.trim;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.transform.FieldSet;

import com.trim.vo.RH_vo;

public class CBI_RH_MultiLineItemReader<T> implements ItemReader<RH_vo>, ItemStream {

	private FlatFileItemReader<FieldSet> delegate;

	public void setDelegate(FlatFileItemReader<FieldSet> delegate) {
		this.delegate = delegate;
	}

	@Override
	public void close() throws ItemStreamException {
		delegate.close();
	}

	@Override
	public void open(ExecutionContext arg0) throws ItemStreamException {
		delegate.open(arg0);
	}

	@Override
	public void update(ExecutionContext arg0) throws ItemStreamException {
		delegate.update(arg0);
	}

	// TODO ottimizzare lettura e gestire errori
	@Override
	public RH_vo read() throws Exception {
		RH_vo t = null;

		for (FieldSet line = null; (line = this.delegate.read()) != null;) {
			String prefix = line.readString("type");

			if (prefix.equals("RH")) {
				t = new RH_vo(); // Record must start with header
				// t.put(prefix, line.getProperties());
				t.setRh(line.getProperties());
			} else if (prefix.equals("EF")) {
				t.setEf(line.getProperties());
				return t; // Record must end with footer
			} else // if other types
			{
				switch (new Integer(prefix)) {
				case 61: {
					if (t.getR61() == null) {
						t.setR61(new ArrayList<Properties>());
					}
					t.getR61().add(line.getProperties());
					break;
				}
				case 62: {
					if (t.getR62() == null) {
						t.setR62(new ArrayList<Properties>());
					}
					t.getR62().add(line.getProperties());
					break;
				}
				case 63: {
					if (t.getR63() == null) {
						t.setR63(new ArrayList<Properties>());
					}
					t.getR63().add(line.getProperties());
					break;
				}
				case 64: {
					if (t.getR64() == null) {
						t.setR64(new ArrayList<Properties>());
					}
					t.getR64().add(line.getProperties());
					break;
				}
				case 65: {
					if (t.getR65() == null) {
						t.setR65(new ArrayList<Properties>());
					}
					t.getR65().add(line.getProperties());
					break;
				}

				}
				// if (t.get(prefix) == null) {
				// t.put(prefix, new ArrayList<Properties>());
				// }
				// ((ArrayList<Properties>)
				// t.get(prefix)).add(line.getProperties());
				// t.put(prefix, line.getProperties());
			}
		}
		// Assert.isNull(t, "No 'END' was found.");
		return null;
	}

}
