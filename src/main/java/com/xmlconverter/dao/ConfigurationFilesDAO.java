package com.xmlconverter.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import ch.qos.logback.classic.Logger;

public class ConfigurationFilesDAO {

	// LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
	Logger logger = (Logger) LoggerFactory.getLogger(getClass());
	Logger human_log = (Logger) LoggerFactory.getLogger("human_log");

	// @Autowired
	// Environment env;

	@Value("${dbProperties.url}")
	private String url;

	@Value("${dbProperties.driverName}")
	private String driverName;

	@Value("${dbProperties.user}")
	private String user;

	@Value("${dbProperties.password}")
	private String password;

	public void updateConfigurationFile(String filename) throws Exception {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driverName);
		dataSource.setUrl(url);
		dataSource.setUsername(user);
		dataSource.setPassword(password);

		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		// TODO test connessione alive
		jdbc.execute("Select 1 from dual");
		DataSourceTransactionManager dstm = new DataSourceTransactionManager(dataSource);

		TransactionDefinition txDef = new DefaultTransactionDefinition();
		TransactionStatus txStatus = dstm.getTransaction(txDef);

		// primo passo load del file
		try {

			// elimino il contenuto del file da database
			jdbc.update("DELETE FROM CONFIGURATION_FILES WHERE FILE_NAME= ? ", filename);
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line;
			int row = 0;
			while ((line = br.readLine()) != null) {
				jdbc.update("INSERT INTO CONFIGURATION_FILES(FILE_NAME, FILE_ROW, LINE_CONTENT) VALUES(?,?,?)",
						filename, row, line);
				row++;
			}
			br.close();
			dstm.commit(txStatus);

		} catch (Exception e) {
			dstm.rollback(txStatus);

			throw e;

		}

	}

	public void downloadConfigurationFile(String filename, String prefix) throws Exception {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driverName);
		dataSource.setUrl(url);
		dataSource.setUsername(user);
		dataSource.setPassword(password);

		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		// TODO test connessione alive
		jdbc.execute("Select 1 from dual");
		DataSourceTransactionManager dstm = new DataSourceTransactionManager(dataSource);

		try {
			BufferedWriter br = new BufferedWriter(new FileWriter(prefix + filename));
			List<Map<String, Object>> result = jdbc.queryForList(
					"SELECT * FROM CONFIGURATION_FILES WHERE FILE_NAME = ? ORDER BY FILE_ROW ASC", filename);
			for (Map<String, Object> row : result) {
				br.write((String) row.get("LINE_CONTENT"));
				br.newLine();
			}
			br.close();

		} catch (Exception e) {

			throw e;

		}

	}

}
