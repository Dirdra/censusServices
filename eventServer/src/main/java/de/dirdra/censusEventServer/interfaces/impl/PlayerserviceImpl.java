package de.dirdra.censusEventServer.interfaces.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.dirdra.census.model.ps2v2.Character;
import de.dirdra.censusEventServer.interfaces.PlayerService;

@Component
public class PlayerserviceImpl implements PlayerService {
	
	@Autowired
	private PlayerserviceRestclient restTemplate;

	@Override
	public String getCharacternameById(String characterID) {
		final Character response = restTemplate.getCharacterById(characterID);
		return response.getName().getFirst();
	}

	public PlayerserviceRestclient getRestTemplate() {
		return restTemplate;
	}
	
	public void setRestTemplate(PlayerserviceRestclient restTemplate) {
		this.restTemplate = restTemplate;
	}	
	
}
