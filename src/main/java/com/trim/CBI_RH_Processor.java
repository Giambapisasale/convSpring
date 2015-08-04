package com.trim;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.trim.schema.CBIGroupHeader42;

public class CBI_RH_Processor implements ItemProcessor<HashMap<String, String>, CBIGroupHeader42> {

	private static Logger logger = LoggerFactory.getLogger(CBI_RH_Processor.class);

	private static DozerBeanMapper mapper = getMapper();

	@Override
	public CBIGroupHeader42 process(HashMap<String, String> item) throws Exception {

		CBIGroupHeader42 header = mapper.map(item, CBIGroupHeader42.class);

		header.setCreDtTm(new Date());

		return header;
	}

	private static DozerBeanMapper getMapper() {
		// TODO load from database
		logger.info("###Loading mapping rules from XML");
		List<String> myMappingFiles = new ArrayList<String>();
		myMappingFiles.add("dozer_mapping.xml");

		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.setMappingFiles(myMappingFiles);
		return mapper;
	}

}
