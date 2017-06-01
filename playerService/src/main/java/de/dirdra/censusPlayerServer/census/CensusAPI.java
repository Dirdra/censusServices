package de.dirdra.censusPlayerServer.census;

import de.dirdra.census.model.ps2v2.Character;

public interface CensusAPI {
	Character getCharacter(String id);
}
