package de.dirdra.censusPlayerServer.mapping;

import de.dirdra.census.model.ps2v2.Single_Character_By_Id;
import de.dirdra.census.model.ps2v2.Times_Type;
import de.dirdra.censusPlayerServer.jpa.repository.model.Character;
import de.dirdra.censusPlayerServer.jpa.repository.model.Times;

public class CensusToRepo {
	public static Character createToJpa(final Single_Character_By_Id censusChar) {
		if (censusChar == null) {
			return null;
		}
		
		final Character result = new Character();
		
		result.setBattlerank(censusChar.getBattle_Rank().getValue());
		result.setCharacterID(String.valueOf(censusChar.getCharacter_Id()));
		result.setFactionID(String.valueOf(censusChar.getFaction_Id()));
		result.setHeadID(String.valueOf(censusChar.getHead_Id()));
		result.setName(censusChar.getName().getFirst());
		result.setProfileID(String.valueOf(censusChar.getProfile_Id()));
		result.setTimedata(createToJpa(censusChar.getTimes()));
		result.setTitleID(String.valueOf(censusChar.getTitle_Id()));
		
		return result;
	}
	
	public static Times createToJpa(final Times_Type timesData) {
		if (timesData == null) {
			return null;
		}
		
		final Times result = new Times();
		
		result.setCreateDate(timesData.getCreation_Date());
		result.setLastLoginDate(timesData.getLast_Login_Date());
		result.setLastRemoteSave(timesData.getLast_Save_Date());
		result.setLoginCount(timesData.getLogin_Count());
		result.setMinutesPlayed(timesData.getMinutes_Played());
		
		return result;
	}
}
