package com.xmlconverter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XSLT_Templates {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Logger human_log = (Logger) LoggerFactory.getLogger("human_log");

	private Templates cachedXSLT;

	public Templates getCachedXSLT() {
		return cachedXSLT;
	}

	public XSLT_Templates(String xslt_file_name) throws FileNotFoundException, TransformerConfigurationException {
		// load the transformer using JAXP
		TransformerFactory transFact = TransformerFactory.newInstance();
		InputStream stylesheet;
		try {
			stylesheet = new FileInputStream(xslt_file_name);

			Source xslt_source = new StreamSource(stylesheet);
			cachedXSLT = transFact.newTemplates(xslt_source);

		} catch (FileNotFoundException e) {
			String msg = "Si e' verificato un errore durante il caricamento delle regole di mappatura, file non trovato: "
					+ xslt_file_name;
			logger.error(msg, e);
			human_log.error(msg);
			throw e;
		} catch (TransformerConfigurationException e) {
			String msg = "Si e' verificato un errore durante il caricamento delle regole di mappatura, "
					+ "le regole definite non sono valide: "
					+ xslt_file_name;
			logger.error(msg, e);
			human_log.error(msg);
			throw e;
		}
	}

}
