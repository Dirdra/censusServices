package de.dirdra.censusPlayerServer.jpa.repository.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Times {
	@Column
	private String createDate;
	
	@Column
	private String lastLoginDate;
	
	@Column
	private String lastRemoteSave;
	
	@Column
	private int loginCount;
	
	@Column
	private int minutesPlayed;

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getLastRemoteSave() {
		return lastRemoteSave;
	}

	public void setLastRemoteSave(String lastRemoteSave) {
		this.lastRemoteSave = lastRemoteSave;
	}

	public int getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(int loginCount) {
		this.loginCount = loginCount;
	}

	public int getMinutesPlayed() {
		return minutesPlayed;
	}

	public void setMinutesPlayed(int minutesPlayed) {
		this.minutesPlayed = minutesPlayed;
	}
}
