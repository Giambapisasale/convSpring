package com.trim;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.trim.schema.CBIDlyStmtReqLogMsg000102;

public class CBI_RH_Processor implements ItemProcessor<HashMap<String, Properties>, CBIDlyStmtReqLogMsg000102> {

	private static Logger logger = LoggerFactory.getLogger(CBI_RH_Processor.class);

	private static DozerBeanMapper mapper = getMapper();

	@Override
	public CBIDlyStmtReqLogMsg000102 process(HashMap<String, Properties> item) throws Exception {

		CBIDlyStmtReqLogMsg000102 header = mapper.map(item, CBIDlyStmtReqLogMsg000102.class);

		//header.getGrpHdr().setCreDtTm(new Date());

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
