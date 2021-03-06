package com.riders.giddy.api.v1;

import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableAutoConfiguration(exclude = {
        org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class})
@EnableJSONDoc
public class GiddyCoreApplication {

    public static void main(final String[] args) {
        SpringApplication.run(GiddyCoreApplication.class, args);
    }
}
