package hello;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
@ImportResource({ "classpath*:job-read-files.xml" })
public class BatchConfiguration {
	@Autowired
	private PlatformTransactionManager transactionManager;
}
