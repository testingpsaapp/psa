package com.psa.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication 
public class ProdSupportAutomationApplication extends SpringBootServletInitializer{

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ProdSupportAutomationApplication.class);
    }

	public static void main(String[] args) {
		SpringApplication.run(ProdSupportAutomationApplication.class, args);
	}
}
