package com.trim;

import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
@ImportResource({ "file:${job_file_name}" })
public class BatchConfiguration extends DefaultBatchConfigurer {

	@Autowired
	private PlatformTransactionManager transactionManager;

}
