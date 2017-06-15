package de.dirdra.censusPlayerServer.census;

import de.dirdra.census.model.ps2v2.Character;

public interface CensusAPI {
	@Deprecated
	Character getCharacter(String id);

	/**
	 * Importieren eines Characters
	 * 
	 * @param id
	 *            ID des Chars bei DBG
	 * @return Flag ob der Import erfolgreich war
	 */
	boolean importCharacter(String id);
}
