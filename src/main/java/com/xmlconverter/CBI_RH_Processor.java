package com.xmlconverter;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.xmlconverter.vo.RH_vo;

public class CBI_RH_Processor implements ItemProcessor<RH_vo, JAXBElement> {

	private Logger logger = LoggerFactory.getLogger(getClass());
	Logger human_log = (Logger) LoggerFactory.getLogger("human_log");

	Object mappeditem;
	QName mappeditem_qname;

	public void setMappeditem_qname(QName mappeditem_qname) {
		this.mappeditem_qname = mappeditem_qname;
	}

	public void setMappeditem(Object mappeditem) {
		this.mappeditem = mappeditem;
	}

	private DozerBeanMapper mapper = getMapper();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public JAXBElement process(RH_vo item) throws Exception {

		human_log.info("Avvio processo di mapping per item: " + item.getRh().toString());

		mappeditem = mapper.map(item, mappeditem.getClass());
		
		JAXBElement mapjaxb = new JAXBElement(mappeditem_qname, mappeditem.getClass(), null, mappeditem);

		return mapjaxb;
	}

	private DozerBeanMapper getMapper() {
		// TODO load from database
		logger.info("###Loading mapping rules from XML");
		List<String> myMappingFiles = new ArrayList<String>();
		myMappingFiles.add("dozer_mapping.xml");

		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.setMappingFiles(myMappingFiles);
		return mapper;
	}

}
