package convSpring;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class Test2 {

	public static void main(String[] args) throws IOException {
		Properties prop =	new Properties();
		prop.load(new FileInputStream("application.properties"));
		
		String url = prop.getProperty("dbProperties.url");
		String driverName = prop.getProperty("dbProperties.driverName");
		String user = prop.getProperty("dbProperties.user");
		String password  = prop.getProperty("dbProperties.pasword");
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driverName);
		dataSource.setUrl(url);
		dataSource.setUsername(user);
		dataSource.setPassword(password);

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.execute("Select 1 from dual");
		
	}
}
