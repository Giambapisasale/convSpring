package com.trim.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class JobListener_setup implements JobExecutionListener {
	private static Logger logger = LoggerFactory.getLogger(JobListener_setup.class);

	@Autowired
	Environment env;
	
	@Value("${dbProperties.url}")
	private String url;
	
	@Value("${dbProperties.driverName}")
	private String driverName;
	
	@Value("${dbProperties.user}")
	private String user;
	
	@Value("${dbProperties.password}")
	private String password;

	
	private JdbcTemplate jdbcTemplate;

	@Override
	public void beforeJob(JobExecution jobExecution) {
		logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@Before Job: " + env.getProperty("db_name"));
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driverName);
		dataSource.setUrl(url);
		dataSource.setUsername(user);
		dataSource.setPassword(password);
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.execute("Select 1 from dual");
		
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@After Job");

	}

}
