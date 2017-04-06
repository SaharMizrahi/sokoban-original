package db;

import java.io.Serializable;

import javax.persistence.Embeddable;
@Embeddable
public class RecordsKey implements Serializable
{

	private String username;
	private int levelID;
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public int getLevelID()
	{
		return levelID;
	}
	public void setLevelID(int levelID)
	{
		this.levelID = levelID;
	}
	public RecordsKey(String username, int levelID) {
		super();
		this.username = username;
		this.levelID = levelID;
	}
	public RecordsKey() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
