package com.xmlconverter.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.xmlconverter.utils.CryptUtils;

import ch.qos.logback.classic.Logger;

public class ConfigurationFilesDAO {

	// LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
	Logger logger = (Logger) LoggerFactory.getLogger(getClass());
	Logger human_log = (Logger) LoggerFactory.getLogger("human_log");

	// @Autowired
	// Environment env;

	@Value("${dbProperties.url}")
	private String url;

	public String getUrl() {
		return url;
	}

	@Value("${dbProperties.driverName}")
	private String driverName;

	@Value("${dbProperties.isCloakWare}")
	private Boolean isCloakWare;

	@Value("${dbProperties.CLOAKWARE_DRIVER_CLASS_NAME}")
	private String cloakWareDriverName;

	@Value("${dbProperties.CLOAKWARE_URL}")
	private String cloakWareUrl;

	@Value("${dbProperties.user}")
	private String user;

	@Value("${dbProperties.password}")
	private String password;

	public void updateConfigurationFile(String filename) throws Exception {
		DriverManagerDataSource dataSource = getDataSource();

		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		// TODO test connessione alive
		jdbc.execute("Select 1 from dual");
		DataSourceTransactionManager dstm = new DataSourceTransactionManager(dataSource);

		TransactionDefinition txDef = new DefaultTransactionDefinition();
		TransactionStatus txStatus = dstm.getTransaction(txDef);

		// primo passo load del file
		try {

			// elimino il contenuto del file da database
			jdbc.update("DELETE FROM AS_APPPRD.XML_CONV_CONFIG WHERE FILE_NAME= ? ", filename);
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line;
			int row = 0;
			while ((line = br.readLine()) != null) {
				jdbc.update("INSERT INTO AS_APPPRD.XML_CONV_CONFIG (FILE_NAME, FILE_ROW, LINE_CONTENT) VALUES(?,?,?)",
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

	private DriverManagerDataSource getDataSource()
			throws Exception {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		if (isCloakWare) {
			Class.forName(cloakWareDriverName).newInstance();
			dataSource.setDriverClassName(driverName);
			dataSource.setUrl(cloakWareUrl);
		} else {
			dataSource.setDriverClassName(driverName);
			dataSource.setUrl(url);
			dataSource.setUsername(user);
			dataSource.setPassword(CryptUtils.decrypt(password));
		}

		return dataSource;
	}

	public String downloadConfigurationFile(String filename, String prefix) throws Exception {
		DriverManagerDataSource dataSource = getDataSource();

		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		// TODO test connessione alive
		jdbc.execute("Select 1 from dual");
		DataSourceTransactionManager dstm = new DataSourceTransactionManager(dataSource);

		try {
			BufferedWriter br = new BufferedWriter(new FileWriter(prefix + filename));
			List<Map<String, Object>> result = jdbc.queryForList(
					"SELECT * FROM AS_APPPRD.XML_CONV_CONFIG WHERE FILE_NAME = ? ORDER BY FILE_ROW ASC", filename);
			for (Map<String, Object> row : result) {

				String line_content = (String) row.get("LINE_CONTENT");
				if (line_content != null) {
					br.write(line_content);
				}
				br.newLine();
			}
			br.close();

		} catch (Exception e) {

			throw e;

		}
		return prefix + filename;

	}

	public ConfigurationFilesDAO() {
		super();
		if (url == null) {
			// provo a caricare le properties dalle system properties

			Properties p = new Properties(System.getProperties());

			url = p.getProperty("dbProperties.url");
			driverName = p.getProperty("dbProperties.driverName");
			isCloakWare = new Boolean(p.getProperty("dbProperties.isCloakWare"));
			cloakWareDriverName = p.getProperty("dbProperties.CLOAKWARE_DRIVER_CLASS_NAME");
			cloakWareUrl = p.getProperty("dbProperties.CLOAKWARE_URL");
			user = p.getProperty("dbProperties.user");
			password = p.getProperty("dbProperties.password");
		}
	}

}
