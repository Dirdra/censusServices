package de.dirdra.censusPlayerServer.census.impl;

import javax.xml.bind.JAXBElement;

import org.apache.camel.StringSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import de.dirdra.census.model.ps2v2.Character;
import de.dirdra.census.model.ps2v2.Character_List;
import de.dirdra.censusPlayerServer.census.CensusAPI;

@Component
public class RestImpl implements CensusAPI {
	
	private static final Logger LOG = LoggerFactory.getLogger(RestImpl.class);
	
	@Value(value = "${dbg.census.url:http://census.daybreakgames.com/{serviceID}/xml/get/ps2:v2/}")
	private String url;
	
	@Value(value = "${dbg.census.serviceID}")
	private String serviceID;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Jaxb2Marshaller marshaller;

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(cacheNames="character")
	public Character getCharacter(String id) {
		LOG.debug("getCharacter id > {}", id);
		
		final String response = restTemplate.getForObject(url+"character?character_id={id}", String.class, serviceID, id);
		LOG.debug("response > {}", response);
		
		
		Character chr = null;
		try {
			Character_List unmarshalledResponse = ((JAXBElement<Character_List>) marshaller.unmarshal(new StringSource(response))).getValue();
			chr = unmarshalledResponse.getCharacter().get(0);
		} catch (Exception e) {
			LOG.warn("Error in reading response of \""+response+"\"", e);
		}
		
		return chr;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getServiceID() {
		return serviceID;
	}

	public void setServiceID(String serviceID) {
		this.serviceID = serviceID;
	}
}
