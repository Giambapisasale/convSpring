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
		ItemProcessListener<Document, String>, ItemReadListener<Document> {

	Logger logger = (Logger) LoggerFactory.getLogger(getClass());
	Logger human_log = (Logger) LoggerFactory.getLogger("human_log");

	String output_file_path;

	public String getOutput_file_path() {
		return output_file_path;
	}

	public void setOutput_file_path(String output_file_path) {
		this.output_file_path = output_file_path;
	}

	@Override
	public void beforeJob(JobExecution jobExecution) {
		logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@Before Job ");

	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@After Job");

		// legge il file output temporaneo e ricopia nel file
		// envelope salvando il file output definitivo
		String output_file = System.getProperty("output_file");
		// String output_file_path = System.getProperty("output_file_path");
		String envelope_file = System.getProperty("envelope_file");
		String output_file_def = System.getProperty("output_file_def");
		String envelope_placeholder = System.getProperty("envelope_placeholder");
		try {

			BufferedReader br = new BufferedReader(new FileReader(output_file));
			BufferedReader brenv = new BufferedReader(new FileReader(envelope_file));
			BufferedWriter bw = new BufferedWriter(new FileWriter(output_file_path + "/" + output_file_def));
			String env_line = null;
			while ((env_line = brenv.readLine()) != null) {
				if (env_line.trim().equals(envelope_placeholder)) {
					String line;
					while ((line = br.readLine()) != null) {
						bw.write(line);
					}
				} else if (env_line.trim().equals("{{NbOfLogMsg}}")) {

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

			// clean file temporanei, TODO deve essere fatto anche in caso di
			// errore
			String job_file_name = System.getProperty("job_file_name");
			String xslt_file = System.getProperty("xslt_file");

			File file_output_file = new File(output_file);
			if (file_output_file.exists())
				file_output_file.delete();

			File file_envelope_file = new File(envelope_file);
			if (file_envelope_file.exists())
				file_envelope_file.delete();

			File file_job_file = new File(job_file_name);
			if (file_job_file.exists())
				file_job_file.delete();

			File file_xslt_file = new File(xslt_file);
			if (file_xslt_file.exists())
				file_xslt_file.delete();

			String tmp_prefix = System.getProperty("tmp_prefix");
			String output_file_def_final = output_file_def.substring(tmp_prefix.length(), output_file_def.length());
			File file_output_def_file = new File(output_file_path + "/" + output_file_def);
			file_output_def_file.renameTo(new File(output_file_path + "/" + output_file_def_final));
			logger.debug("File saved to: " + output_file_def_final);

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

		// cleanup
		String job_file_name = System.getProperty("job_file_name");
		String xslt_file = System.getProperty("xslt_file");
		String output_file = System.getProperty("output_file");
		String envelope_file = System.getProperty("envelope_file");

		File file_output_file = new File(output_file);
		if (file_output_file.exists())
			file_output_file.delete();

		File file_envelope_file = new File(envelope_file);
		if (file_envelope_file.exists())
			file_envelope_file.delete();

		File file_job_file = new File(job_file_name);
		if (file_job_file.exists())
			file_job_file.delete();

		File file_xslt_file = new File(xslt_file);
		if (file_xslt_file.exists())
			file_xslt_file.delete();

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
