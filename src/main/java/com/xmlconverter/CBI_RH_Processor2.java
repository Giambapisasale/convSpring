package com.xmlconverter;

import javax.xml.transform.Transformer;

import org.dom4j.Document;
import org.dom4j.io.DocumentResult;
import org.dom4j.io.DocumentSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class CBI_RH_Processor2 implements ItemProcessor<Document, String> {

	private Logger logger = LoggerFactory.getLogger(getClass());
	Logger human_log = (Logger) LoggerFactory.getLogger("human_log");
	
	@Override
	public String process(Document item) throws Exception {
		
		Transformer transformer = getTemplate().getCachedXSLT().newTransformer();

		// applica la trasformazione xslt
		DocumentSource source = new DocumentSource(item);
		DocumentResult result = new DocumentResult();
		transformer.transform(source, result);

		// restituisce il documento trasformato
		Document transformedDoc = result.getDocument();

		return transformedDoc.getRootElement().asXML();
	}
	
	private XSLT_Templates template;

	public XSLT_Templates getTemplate() {
		return template;
	}

	public void setTemplate(XSLT_Templates templates) {
		this.template = templates;
	}


}
