package com.xmlconverter;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

import org.dom4j.Document;
import org.dom4j.io.DocumentResult;
import org.dom4j.io.DocumentSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;

public class CBI_RH_Processor2 implements ItemProcessor<Document, String> {

	private Logger logger = LoggerFactory.getLogger(getClass());
	Logger human_log = (Logger) LoggerFactory.getLogger("human_log");

	// todo
	@Value(value = "${xslt_file}")
	private String xslt_file_name;

	public void setXslt_file(String xslt_file) {
		this.xslt_file_name = xslt_file;
	}

	@Override
	public String process(Document item) throws Exception {
		// load the transformer using JAXP
		// FIXME load once! caricare solo una volta possibilmente come bean
		// configurato singleton
		TransformerFactory factory = TransformerFactory.newInstance();
		File xslt_file = new File(xslt_file_name);
		InputStream stylesheet = new FileInputStream(xslt_file);
		Transformer transformer = factory.newTransformer(new StreamSource(stylesheet));

		// now lets style the given document
		DocumentSource source = new DocumentSource(item);
		DocumentResult result = new DocumentResult();
		transformer.transform(source, result);

		// return the transformed document
		Document transformedDoc = result.getDocument();

		return transformedDoc.getRootElement().asXML();
	}

}
