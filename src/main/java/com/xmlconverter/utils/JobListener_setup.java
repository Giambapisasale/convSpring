package com.xmlconverter.utils;

//import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;

public class JobListener_setup implements JobExecutionListener {
//	private static Logger logger = LoggerFactory.getLogger(JobListener_setup.class);

//	 LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
	 Logger logger = (Logger) LoggerFactory.getLogger(JobListener_setup.class);
	 Logger human_log = (Logger) LoggerFactory.getLogger("human_log");
	 
//	@Autowired
//	Environment env;
	
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
		logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@Before Job ");
		human_log.error("Test error human readable");
//		logger.getAppender("STDOUT").addError("testerror");
		
//		//test connessione
//		
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName(driverName);
//		dataSource.setUrl(url);
//		dataSource.setUsername(user);
//		dataSource.setPassword(password);
//		
//		jdbcTemplate = new JdbcTemplate(dataSource);
//		jdbcTemplate.execute("Select 1 from dual");
		
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@After Job");

	}

}
