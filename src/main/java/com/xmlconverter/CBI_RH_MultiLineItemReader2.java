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

	private static final String X_TAG_PREFIX = "x";

	private static final String STRUCTURE_FLAG_FIELD_NAME = "structure_flag";

	private static final String TYPE_FIELD_NAME = "type";

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

	@Override
	public Document read() throws Exception {
		Document document = DocumentHelper.createDocument();
		Element societa = null;

		for (FieldSet line = null; (line = this.delegate.read()) != null;) {
			String prefix = line.readString(TYPE_FIELD_NAME);

			// "costruzione" di un Document a partire dalla struttura dati
			// che rappresenta l'input
			// ogni nodo, categorizzato cal campo "type", viene
			// agganciato al dom nella posizione indicata dalla stringa
			// XPATH associata nella mappa keyToPath
			if (prefix.equals(header)) {
				societa = document.addElement(header);
				fromFieldSetToElement(societa, line);
			} else if (prefix.equals(footer)) {
				return document; // Record must end with footer
			} else // if other types
			{
				// recupera la posizione all'interno del DOM in pase al type del
				// record
				// se è presente il campo structure_flag viene inserito come
				// attributo per facilitare la selezione (in particolare dei
				// record 63)
				// FIXME i campi 63 consecutivi devono essere uniti, (magari in
				// xslt??)

				String path = keyToPath.get(prefix);
				logger.info("@@Per la chiave: " + prefix);
				logger.info("@@trovato path: " + path);
				Node node = document.selectSingleNode(path);

				// TODO controllare node, se non presente allora file input
				// malformato e lanciare eccezione
				Element item = ((Element) node).addElement(X_TAG_PREFIX + prefix);
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
		if (line.getProperties().containsKey(STRUCTURE_FLAG_FIELD_NAME)) {
			String structure_flag = line.readString(STRUCTURE_FLAG_FIELD_NAME);
			elem.addAttribute(STRUCTURE_FLAG_FIELD_NAME, structure_flag);
		}
		return;
	}

}
