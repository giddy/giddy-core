package com.riders.giddy;

import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableJSONDoc
public class GiddyCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(GiddyCoreApplication.class, args);
	}
}
