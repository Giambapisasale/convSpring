package com.xmlconverter;

import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.transform.FieldSet;

public class CBI_RH_MultiLineItemReader2<T> implements ItemReader<Document>, ItemStream {

	private Logger logger = LoggerFactory.getLogger(getClass());

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

	private String header;
	private String footer;
	private Map<String, String> keyToPath;

	public void setKeyToPath(Map<String, String> keyToPath) {
		this.keyToPath = keyToPath;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getFooter() {
		return footer;
	}

	public void setFooter(String footer) {
		this.footer = footer;
	}

	// TODO ottimizzare lettura e gestire errori
	@Override
	public Document read() throws Exception {
		Document document = DocumentHelper.createDocument();
		Element societa = null;

		for (FieldSet line = null; (line = this.delegate.read()) != null;) {
			String prefix = line.readString("type");

			if (prefix.equals(header)) {
				societa = document.addElement(header);
				fromFieldSetToElement(societa, line);
			} else if (prefix.equals(footer)) {
				return document; // Record must end with footer
			} else // if other types
			{
				String path = keyToPath.get(prefix);
				logger.info("@@Per la chiave: " + prefix);
				logger.info("@@trovato path: " + path);
				Node node = document.selectSingleNode(path);
				Element item = ((Element) node).addElement("x" + prefix);
				fromFieldSetToElement(item, line);

			}
		}
		// Assert.isNull(t, "No 'END' was found.");
		return null;
	}

	private void fromFieldSetToElement(Element elem, FieldSet line) {
		for (String chiave : line.getNames()) {
			String valore = line.readString(chiave);
			Element item = elem.addElement(chiave);
			item.setText(valore);
		}
		return;
	}

}
