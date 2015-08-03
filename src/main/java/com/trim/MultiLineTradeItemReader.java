package com.trim;

import java.util.HashMap;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.transform.FieldSet;

public class MultiLineTradeItemReader<T> implements ItemReader<HashMap<String,String>>, ItemStream  {
	
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

	
	@Override
	public HashMap<String,String> read() throws Exception {
		HashMap<String,String> t = null;

	    for (FieldSet line = null; (line = this.delegate.read()) != null;) {
	        String prefix = line.readString("type");
	        
	        String value = line.readString("content");
			
	        
	        if (prefix.equals("RH")) {
	            t = new HashMap<String,String>(); // Record must start with header
	        }
	        else if (prefix.equals("EF")) {
	            return t; // Record must end with footer
	        }
	        else // if other types
	        {
	        	t.put(prefix, value);
	        }
	    }
//	    Assert.isNull(t, "No 'END' was found.");
	    return null;
	}
}
