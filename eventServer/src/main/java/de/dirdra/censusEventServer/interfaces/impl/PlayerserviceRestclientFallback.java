package de.dirdra.censusEventServer.interfaces.impl;

import de.dirdra.census.model.ps2v2.Character;

public class PlayerserviceRestclientFallback implements PlayerserviceRestclient {

	@Override
	public Character getCharacterById(String id) {
		throw new RuntimeException("Playerservice unreachable");
	}

}
