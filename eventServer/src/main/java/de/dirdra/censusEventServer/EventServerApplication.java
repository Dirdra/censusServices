package de.dirdra.censusEventServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages="de.dirdra.censusEventServer")
public class EventServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventServerApplication.class, args);
	}
}
