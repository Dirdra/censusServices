package de.dirdra.censusEventServer.interfaces.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import de.dirdra.census.model.ps2v2.Character;

public class PlayerserviceRestclientFallback implements PlayerserviceRestclient {
	
	private static final Logger LOG = LoggerFactory.getLogger(PlayerserviceRestclientFallback.class);

	@Override
	public Character getCharacterById(String id) {
		LOG.debug("Fehler bei getCharacterById");
		return null;
	}
	
	@Override
	public ResponseEntity<de.dirdra.censusPlayerServer.jpa.repository.model.Character> getPersistentChar(String id) {
		LOG.warn("Fehler bei getPersistentChar");
		return null;
	}
	
	@Override
	public ResponseEntity<de.dirdra.censusPlayerServer.jpa.repository.model.Character> importCharToDB(String id) {
		LOG.warn("Fehler bei importCharToDB");
		return null;
	}

}
