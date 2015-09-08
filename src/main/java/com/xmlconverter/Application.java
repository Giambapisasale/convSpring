package com.xmlconverter;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.xmlconverter.dao.ConfigurationFilesDAO;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws Exception {

		//job setup, scarica i file di configurazione
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSS");

		// forza il caricamento delle system properties per popolare il DAO
		FileInputStream propFile = new FileInputStream("application.properties");
		Properties p = new Properties(System.getProperties());
		p.load(propFile);
		System.setProperties(p);

		//TODO check file existence
		ConfigurationFilesDAO daoConf = new ConfigurationFilesDAO();
		String job_file_name = p.getProperty("job_file_name");
		String xslt_file = p.getProperty("xslt_file");
		String prefix = df.format(new Date());

		String newJname = daoConf.downloadConfigurationFile(job_file_name, prefix);
		System.setProperty("job_file_name", newJname);
		
		String newXSLTName = daoConf.downloadConfigurationFile(xslt_file, prefix);
		System.setProperty("xslt_file", newXSLTName);

		SpringApplication.run(Application.class, args);
		
		//TODO AFTER JOB "incastrare" il file in output in un modello precompilato che 
		// contiene il nodo root del file XML completo di destinazione
		// e verificare il requisito sulla produzione dei file in output 
	}
}
