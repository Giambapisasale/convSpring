package convSpring;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Properties;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.xmlconverter.utils.CryptUtils;

public class Test2 {

	public static void main(String[] args) throws Exception {
		Properties prop = new Properties();
		prop.load(new FileInputStream("application.properties"));

		String filename = "RH_transform.xslt";

		String url = prop.getProperty("dbProperties.url");
		String driverName = prop.getProperty("dbProperties.driverName");
		String user = prop.getProperty("dbProperties.user");
		String password = prop.getProperty("dbProperties.password");

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driverName);
		dataSource.setUrl(url);
		dataSource.setUsername(user);
		dataSource.setPassword(CryptUtils.decrypt(password));

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
				System.out.println(line);
				jdbc.update("INSERT INTO AS_APPPRD.XML_CONV_CONFIG(FILE_NAME, FILE_ROW, LINE_CONTENT) VALUES(?,?,?)",
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

}
