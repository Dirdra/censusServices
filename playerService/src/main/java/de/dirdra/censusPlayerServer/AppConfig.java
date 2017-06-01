package de.dirdra.censusPlayerServer;

import org.springframework.context.annotation.Bean;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import de.dirdra.census.model.ps2v2.util.Constants;

@Component
public class AppConfig {
	@Bean
	public RestTemplate createRestTemplate() {
		final RestTemplate template = new RestTemplate();
		return template;
	}

	@Bean
	public Jaxb2Marshaller createMarshaller() {
		final Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath(Constants.CONTEXT_PATH);
		return marshaller;
	}
}
