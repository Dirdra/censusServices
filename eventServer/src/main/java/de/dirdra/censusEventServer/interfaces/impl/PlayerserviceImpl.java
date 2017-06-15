package de.dirdra.censusEventServer.interfaces.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import de.dirdra.census.model.ps2v2.Character;
import de.dirdra.censusEventServer.interfaces.PlayerService;

@Component
public class PlayerserviceImpl implements PlayerService {
	
	@Autowired
	private PlayerserviceRestclient restTemplate;

	@Override
	public String getCharacternameById(String characterID) {
//		final Character response = restTemplate.getCharacterById(characterID);
		
		ResponseEntity<de.dirdra.censusPlayerServer.jpa.repository.model.Character> response = restTemplate.getPersistentChar(characterID);
		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody().getName();
		} else {
			return null;
		}
	}

	public PlayerserviceRestclient getRestTemplate() {
		return restTemplate;
	}
	
	public void setRestTemplate(PlayerserviceRestclient restTemplate) {
		this.restTemplate = restTemplate;
	}	
	
}
