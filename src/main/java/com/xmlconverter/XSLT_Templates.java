package com.xmlconverter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

public class XSLT_Templates {

	private Templates cachedXSLT;

	public Templates getCachedXSLT() {
		return cachedXSLT;
	}

	public XSLT_Templates(String xslt_file_name) {
		// load the transformer using JAXP
		TransformerFactory transFact = TransformerFactory.newInstance();
		InputStream stylesheet;
		try {
			stylesheet = new FileInputStream(xslt_file_name);

			Source xslt_source = new StreamSource(stylesheet);
			cachedXSLT = transFact.newTemplates(xslt_source);

		} catch (FileNotFoundException e) {
			// TODO gestire errore
			// logger.error
			// throw exception
		} catch (TransformerConfigurationException e) {
			// logger.error
			// throw exception
		}
	}

}
