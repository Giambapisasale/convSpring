package com.trim;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.transform.FieldSet;

import com.trim.vo.RH_vo;
import com.trim.vo.RH_vo61;
import com.trim.vo.RH_vo62;

import junit.framework.Assert;

public class CBI_RH_MultiLineItemReader<T> implements ItemReader<RH_vo>, ItemStream {

	private static Logger logger = LoggerFactory.getLogger(CBI_RH_MultiLineItemReader.class);

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
					Assert.assertNotNull("Invalid record position for 61, missing header", t.getRh());
					RH_vo61 r61 = new RH_vo61();
					r61.setData(line.getProperties());
					t.getR61().add(r61);
					break;
				}
				case 62: {
					Assert.assertTrue("Invalid record position for 62, missing opening balance 61",
							t.getR61().size() > 0);
					Assert.assertNotNull("Invalid record position for 62, missing header", t.getRh());

					try {
						// associo il record 62 all'ultimo r61 inserito
						RH_vo61 r61 = t.getR61().get(t.getR61().size() - 1);
						RH_vo62 r62 = new RH_vo62();
						r62.setData(line.getProperties());
						// creo nuova riga 62 e collego r61 e rh (header)
						r62.setR61(r61);
						r62.setRh(t.getRh());
						r61.getR62().add(r62);
					} catch (Exception e) {
						logger.error("Invalid record position for 63", e);
						// TODO exit, in caso di errore occorre scartare tutto
						// il file
					}
					break;
				}
				case 63: {
					// associo il record 63 all'ultimo r62 inserito
					try {
						RH_vo61 r61 = t.getR61().get(t.getR61().size() - 1);						
						RH_vo62 r62 = r61.getR62().get(r61.getR62().size() - 1);
						r62.getR63().add(line.getProperties());
					} catch (Exception e) {
						logger.error("Invalid record position for 63", e);
						// TODO exit, in caso di errore occorre scartare tutto
						// il file
					}
					break;
				}
				case 64: {
					t.setR64(line.getProperties());
					break;
				}
				case 65: {
					t.setR65(line.getProperties());
					break;
				}

				}
			}
		}
		// Assert.isNull(t, "No 'END' was found.");
		return null;
	}

}
