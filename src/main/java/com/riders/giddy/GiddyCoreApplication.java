package com.riders.giddy;

import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * TODO Add Javadoc comment.
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude = {
        org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class})
@EnableJSONDoc
public class GiddyCoreApplication {

    public static void main(final String[] args) {
        SpringApplication.run(GiddyCoreApplication.class, args);
    }
}
