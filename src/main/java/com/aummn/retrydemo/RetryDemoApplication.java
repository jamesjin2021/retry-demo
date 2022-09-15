package com.aummn.retrydemo;

import com.aummn.retrydemo.service.RetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.support.RetryTemplate;

import java.sql.SQLException;

@SpringBootApplication
public class RetryDemoApplication implements CommandLineRunner {

	@Autowired
	private RetryService service;

	@Autowired
	private RetryTemplate retryTemplate;


	public static void main(String[] args) {
		SpringApplication.run(RetryDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		service.retryService();

	}

	private void callWithRetryTemplate() throws SQLException {
		retryTemplate.execute(arg0 -> {
			service.templateRetryService("");
			return null;
		});
	}
}
