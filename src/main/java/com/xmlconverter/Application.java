package com.xmlconverter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.xmlconverter.dao.ConfigurationFilesDAO;

import ch.qos.logback.classic.Logger;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws Exception {

		Logger logger = (Logger) LoggerFactory.getLogger(Application.class);
		Logger human_log = (Logger) LoggerFactory.getLogger("human_log");
		
		try {
			// job setup, scarica i file di configurazione
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSS");

			// forza il caricamento delle system properties per popolare il DAO
			FileInputStream propFile = new FileInputStream("application.properties");
			Properties p = new Properties(System.getProperties());
			p.load(propFile);
			System.setProperties(p);

			String tmp_prefix = "_tmp_";
			System.setProperty("tmp_prefix", tmp_prefix);

			String id_istanza = p.getProperty("id_istanza");
			String prefix = tmp_prefix + id_istanza + df.format(new Date());

			// TODO check file existence
			ConfigurationFilesDAO daoConf = new ConfigurationFilesDAO();
			String job_file_name = p.getProperty("job_file_name");
			String xslt_file = p.getProperty("xslt_file");
			String envelope_file = p.getProperty("envelope_file");

			// scarica il file descrittivo del job
			String newJname = daoConf.downloadConfigurationFile(job_file_name, prefix);
			System.setProperty("job_file_name", newJname);

			// scarica il file con le regole di mapping (trasformazione xslt)
			String newXSLTName = daoConf.downloadConfigurationFile(xslt_file, prefix);
			System.setProperty("xslt_file", newXSLTName);

			// salva il nome definitivo del file output e imposta il nome del file
			// output temporaneo
			String output_file = p.getProperty("output_file");
			System.setProperty("output_file_def", tmp_prefix + id_istanza + output_file);
			System.setProperty("output_file", prefix + output_file);

			// scarica il file con l'envelope fisico che contiene il messaggio
			// logico
			String newenvelope_file = daoConf.downloadConfigurationFile(envelope_file, prefix);
			System.setProperty("envelope_file", newenvelope_file);
			
			// controlla esistenza file input
			String input_file = System.getProperty("input_file");
			if (!(new File(input_file).exists())) {
				throw new FileNotFoundException("input file non trovato");
			}
		} catch (Exception e) {
			String msg = "Errore durante la configurazione iniziale dell'applicazione: ";
			logger.error(msg, e);
			human_log.error(msg + e.getMessage());
			System.exit(1);
		}

		// SpringApplication app = new SpringApplication(Application.class);

		SpringApplication.run(Application.class, args);

	}
}
