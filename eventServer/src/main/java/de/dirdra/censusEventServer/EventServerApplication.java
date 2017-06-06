package de.dirdra.censusEventServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages="de.dirdra.censusEventServer")
@EnableDiscoveryClient
@EnableFeignClients
public class EventServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventServerApplication.class, args);
	}
}
