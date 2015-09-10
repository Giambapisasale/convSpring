package com.xmlconverter.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import ch.qos.logback.classic.Logger;

public class JobListener_setup implements JobExecutionListener {

	Logger logger = (Logger) LoggerFactory.getLogger(getClass());
	Logger human_log = (Logger) LoggerFactory.getLogger("human_log");

	@Override
	public void beforeJob(JobExecution jobExecution) {
		logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@Before Job ");
		human_log.error("Test error human readable");

	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@After Job");

		// legge il file output temporaneo e ricopia nel file
		// envelope salvando il file output definitivo
		String output_file = System.getProperty("output_file");
		String envelope_file = System.getProperty("envelope_file");
		String output_file_def = System.getProperty("output_file_def");
		String envelope_placeholder = System.getProperty("envelope_placeholder");
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(output_file));
			BufferedReader brenv = new BufferedReader(new FileReader(envelope_file));
			BufferedWriter bw = new BufferedWriter(new FileWriter(output_file_def));
			String env_line = null;
			while( (env_line = brenv.readLine()) != null) {
				if(env_line.trim().equals(envelope_placeholder)) {
					String line;
					while( (line = br.readLine()) != null) {
						bw.write(line);
					}
				} else {
					bw.write(env_line);
				}
			}
			br.close();
			brenv.close();
			bw.flush();
			bw.close();

			// clean file temporanei, TODO deve essere fatto anche in caso di errore
			new File(output_file).delete();
			new File(envelope_file).delete();
			String job_file_name = System.getProperty("job_file_name");
			String xslt_file = System.getProperty("xslt_file");
			new File(job_file_name).delete();
			new File(xslt_file).delete();
			
		} catch (FileNotFoundException e) {
			String msg = "Errore durante la lettura del file creato o del file di envelope: ";
			human_log.error(msg, e);
			logger.error(msg + e.getMessage(), e); 
			// FIXME gestire ritorno errore
		} catch (IOException e) {
			String msg = "Errore durante la scrittura del file definitivo: ";
			human_log.error(msg, e);
			logger.error(msg + e.getMessage(), e); 
			// FIXME gestire ritorno errore
		}

	}

}
