package com.trim;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.trim.schema.CBIDlyStmtReqLogMsg000102;
import com.trim.vo.RH_vo;

public class CBI_RH_Processor implements ItemProcessor<RH_vo, CBIDlyStmtReqLogMsg000102> {

	private static Logger logger = LoggerFactory.getLogger(CBI_RH_Processor.class);

	private static DozerBeanMapper mapper = getMapper();

	@Override
	public CBIDlyStmtReqLogMsg000102 process(RH_vo item) throws Exception {

		CBIDlyStmtReqLogMsg000102 header = mapper.map(item, CBIDlyStmtReqLogMsg000102.class);		


		// header.getGrpHdr().setCreDtTm(new Date());

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
