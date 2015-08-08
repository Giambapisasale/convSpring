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

public class CBI_RH_MultiLineItemReader<T> implements ItemReader<HashMap<String, Object>>, ItemStream {

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
	public HashMap<String, Object> read() throws Exception {
		HashMap<String, Object> t = null;

		for (FieldSet line = null; (line = this.delegate.read()) != null;) {
			String prefix = line.readString("type");

			if (prefix.equals("RH")) {
				t = new HashMap<String, Object>(); // Record must start with
														// header
				t.put(prefix, line.getProperties());
			} else if (prefix.equals("EF")) {
				return t; // Record must end with footer
			} else // if other types
			{	
				if(t.get(prefix) == null) {
					t.put(prefix,new ArrayList<Properties>());
				}
				((ArrayList<Properties>)t.get(prefix)).add(line.getProperties());
//				t.put(prefix, line.getProperties());
			}
		}
		// Assert.isNull(t, "No 'END' was found.");
		return null;
	}
}
