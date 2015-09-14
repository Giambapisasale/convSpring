package com.xmlconverter.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.dom4j.Document;
//import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.ItemProcessListener;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

import ch.qos.logback.classic.Logger;

public class JobListener_setup implements JobExecutionListener, StepExecutionListener, ItemWriteListener<String>,
		ItemProcessListener<Document, String>, ItemReadListener<Document>{

	Logger logger = (Logger) LoggerFactory.getLogger(getClass());
	Logger human_log = (Logger) LoggerFactory.getLogger("human_log");

	@Override
	public void beforeJob(JobExecution jobExecution) {
		logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@Before Job ");
		human_log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@Before Job");

	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@After Job");
		human_log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@After Job");
		
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
			while ((env_line = brenv.readLine()) != null) {
				if (env_line.trim().equals(envelope_placeholder)) {
					String line;
					while ((line = br.readLine()) != null) {
						bw.write(line);
					}
				}
				if (env_line.trim().equals("{{NbOfLogMsg}}")) {

					bw.write("" + writeCount);

				} else {
					bw.write(env_line);
				}
			}

			br.close();
			brenv.close();
			bw.flush();
			bw.close();

			logger.info("@@@ totale righe scritte:" + writeCount);
			human_log.info("@@@ totale righe scritte:" + writeCount);
			// clean file temporanei, TODO deve essere fatto anche in caso di
			// errore
			new File(output_file).delete();
			new File(envelope_file).delete();
			String job_file_name = System.getProperty("job_file_name");
			String xslt_file = System.getProperty("xslt_file");
			new File(job_file_name).delete();
			new File(xslt_file).delete();

			String tmp_prefix = System.getProperty("tmp_prefix");
			String output_file_def_final = output_file_def.substring(tmp_prefix.length(), output_file_def.length());
			new File(output_file_def).renameTo(new File(output_file_def_final));

		} catch (FileNotFoundException e) {
			String msg = "Errore durante la lettura del file creato o del file di envelope: ";
			human_log.error(msg, e);
			logger.error(msg + e.getMessage(), e);
			exitApplicationWithError(msg, e);
		} catch (IOException e) {
			String msg = "Errore durante la scrittura del file definitivo: ";
			human_log.error(msg, e);
			logger.error(msg + e.getMessage(), e);
			exitApplicationWithError(msg, e);
		}

	}

	@Override
	public void beforeStep(StepExecution stepExecution) {
		logger.info("@@Start step");
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		writeCount = stepExecution.getWriteCount();
		return stepExecution.getExitStatus();
	}

	private int writeCount = 0;

	@Override
	public void beforeWrite(List items) {
		logger.debug("Before write " + items.size() + " items");
	}

	@Override
	public void afterWrite(List items) {
		logger.debug("After write " + items.size() + " items");
	}

	@Override
	public void onWriteError(Exception exception, List items) {
		exitApplicationWithError("Chiusura per errore in scrittura", exception);
	}

	@Override
	public void beforeProcess(Document item) {
		logger.debug("Before process " + item.getPath() + " item");

	}

	@Override
	public void afterProcess(Document item, String result) {
		logger.debug("Ater process " + item.getPath() + " item");

	}

	@Override
	public void onProcessError(Document item, Exception e) {
		exitApplicationWithError("Chiusura per errore in process", e);

	}

	private void exitApplicationWithError(String msg, Exception exception) {
		logger.error(msg, exception);
		human_log.error("Applicazione terminata per errore: " + msg);

		// TODO cleanup

		System.exit(1);
	}

	@Override
	public void beforeRead() {
		logger.debug("Before read ");
		
	}

	@Override
	public void afterRead(Document item) {
		logger.debug("Ater read " + item.getPath() + " item");
	}

	@Override
	public void onReadError(Exception ex) {
		exitApplicationWithError("Chiusura per errore in lettura", ex);
	}
}
