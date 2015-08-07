package com.trim;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	public static void main(String[] args) throws ParseException {

		SimpleDateFormat df = new SimpleDateFormat("ddMMyy");
		Date date = df.parse("300881");
		
		Calendar c = Calendar.getInstance();
		
		c.setTime(date);
		
		System.out.println(date);
		System.out.println(df.format(date));

		System.out.println(df.getTimeZone());
//		App obj = new App();
//		obj.run();

	}

	private void run() {

		String[] springConfig = { "/context.xml", "/job-read-files.xml" };

		ApplicationContext context;
		try {
			context = new ClassPathXmlApplicationContext(springConfig);
		} catch (BeansException e1) {
			e1.printStackTrace();
			return;
		}
		// FileSystemXmlApplicationContext ??

		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job) context.getBean("readMultiFileJob");

		try {

			JobExecution execution = jobLauncher.run(job, new JobParameters());
			System.out.println("Exit Status : " + execution.getStatus());
			System.out.println("Exit Status : " + execution.getAllFailureExceptions());

		} catch (Exception e) {
			e.printStackTrace();

		}

		System.out.println("Done");

	}

}
