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
	private Logger human_log = (Logger) LoggerFactory.getLogger("human_log");

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

	/**
	 * questo valore viene prefissato al type per evitare tag di tipo numerico
	 */
	private String x_tag_prefix = "x";

	/**
	 * il tipo di record che definisce l'inizio di un blocco di informazioni
	 */
	private String header;

	/**
	 * il tipo di record che definisce la fine di un blocco di informazioni
	 */
	private String footer;

	/**
	 * Questa mappa viene utilizzata per costruire una struttura dati complessa
	 * a partire da un blocco di record consecutivi Ogni chiave rappresenta un
	 * record di tipo definito dal campo type_property mentre il valore
	 * associato alla chiave raprresenta il path XPath utilizzato per
	 * "agganciare" il nodo corrente all'interno del frammento XML in fase di
	 * creazione. Il frammento di XML viene aperto a partire da un record di
	 * tipo header e chiuso da un record di tipo footer.
	 */
	private Map<String, String> keyToPath;

	/**
	 * questa variabile viene utilizzata per discriminare il tipo di record
	 */
	private String type_property;

	/**
	 * la property definita in questo campo verra' impostata come attributo per
	 * facilitare il filtro, come nel caso dei campi 63 per lo structure_flag
	 */
	private String attribute_property;

	/**
	 * la property definita in questo campo verra' unita in caso di due record
	 * consecutivi con identico type_property e attribute_property
	 */
	private String consecutive_join_property = "description";

	public void setConsecutive_join_property(String consecutive_join_property) {
		this.consecutive_join_property = consecutive_join_property;
	}

	public void setType_property(String type_property) {
		this.type_property = type_property;
	}

	public void setAttribute_property(String attribute_property) {
		this.attribute_property = attribute_property;
	}

	public void setKeyToPath(Map<String, String> keyToPath) {
		this.keyToPath = keyToPath;
	}

	public void setx_tag_prefix(String x_tag_prefix) {
		this.x_tag_prefix = x_tag_prefix;
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
		Element lastRow = null;

		try {

			for (FieldSet line = null; (line = this.delegate.read()) != null;) {
				String rowType = line.readString(type_property);
				human_log.debug("Lettura della riga " + rowType);

				// "costruzione" di un Document a partire dalla struttura dati
				// che rappresenta l'input
				// ogni nodo, categorizzato cal campo "type", viene
				// agganciato al dom nella posizione indicata dalla stringa
				// XPATH associata nella mappa keyToPath
				if (rowType.equals(header)) {
					societa = document.addElement(header);
					fromFieldSetToElement(societa, line);
				} else if (rowType.equals(footer)) {
					return document; // Record must end with footer
				} else // if other types
				{
					// recupera la posizione all'interno del DOM in pase al type
					// del record se e' presente il campo structure_flag viene
					// inserito come attributo per facilitare la selezione (in
					// particolare dei record 63)

					String path = keyToPath.get(rowType);
					logger.debug("@@Per la chiave: " + rowType + " @@trovato path: " + path);
					Node node = document.selectSingleNode(path);

					// TODO controllare node, se non presente allora file input
					// malformato e lanciare eccezione
					Element item = ((Element) node).addElement(x_tag_prefix + rowType);
					fromFieldSetToElement(item, line);

					// fix elementi consecutivi
					// se trovo due elementi consecutivi faccio append del campo
					// consecutive_join_property

					Element typeElem = item.element(type_property);
					Element flagElem = item.element(attribute_property);
					// verifico che sia presente il record precedente e che
					// abbiano
					// lo stesso type e che sia presente in entrambi il tag da
					// unire
					if (lastRow != null && typeElem != null && typeElem.getText() != null) {
						Element lasttypeElem = lastRow.element(type_property);
						if (lasttypeElem.getText() != null && lasttypeElem.getText().equals(typeElem.getText())) {
							// se presente attribute_property devono essere
							// entrambi null o avere lo stesso valore
							Element lastflagElem = lastRow.element(attribute_property);
							if ((lastflagElem == null && flagElem == null) || (lastflagElem != null && flagElem != null
									&& lastflagElem.getText().equals(flagElem.getText()))) {

								Element lastdescription = lastRow.element(consecutive_join_property);
								Element description = item.element(consecutive_join_property);
								if (description != null && lastdescription != null) {
									lastdescription.setText(lastdescription.getText() + description.getText());
									logger.debug("@@@@ Trovati record consecutivi: " + lastdescription.getText());
									// stacco l'elemento consecutivo dopo averne
									// salvato il contenuto
									item.detach();
								}
							}
						}
					}

					lastRow = item;
				}

			}
		} catch (Exception e) {
			String msg = "Si e' verificato un errore durante la lettura del file di input: " + e.getMessage();
			human_log.error(msg);
			logger.error(msg, e);
			throw e;
		}
		return null;
	}

	private void fromFieldSetToElement(Element elem, FieldSet line) {
		for (String chiave : line.getNames()) {
			String valore = line.readString(chiave);
			Element item = elem.addElement(chiave);
			item.setText(valore);
		}
		if (line.getProperties().containsKey(attribute_property)) {
			String structure_flag = line.readString(attribute_property);
			elem.addAttribute(attribute_property, structure_flag);
		}
		return;
	}

}
