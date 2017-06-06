package de.dirdra.censusPlayerServer.jpa.repository.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Character {
	
	@Id
	private String characterID;
	
	@Column
	private String name;
	
	@Column
	private int battlerank;
	
	@Column
	private String factionID;
	
	@Column
	private String headID;
	
	@Column
	private String profileID;
	
	@Column
	private String titleID;
	
	@Column
	private Times timedata;

	public String getCharacterID() {
		return characterID;
	}

	public void setCharacterID(String characterID) {
		this.characterID = characterID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBattlerank() {
		return battlerank;
	}

	public void setBattlerank(int battlerank) {
		this.battlerank = battlerank;
	}

	public String getFactionID() {
		return factionID;
	}

	public void setFactionID(String factionID) {
		this.factionID = factionID;
	}

	public String getHeadID() {
		return headID;
	}

	public void setHeadID(String headID) {
		this.headID = headID;
	}

	public String getProfileID() {
		return profileID;
	}

	public void setProfileID(String profileID) {
		this.profileID = profileID;
	}

	public String getTitleID() {
		return titleID;
	}

	public void setTitleID(String titleID) {
		this.titleID = titleID;
	}

	public Times getTimedata() {
		return timedata;
	}

	public void setTimedata(Times timedata) {
		this.timedata = timedata;
	}
}
