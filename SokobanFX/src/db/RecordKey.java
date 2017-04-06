package db;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RecordKey implements Serializable
{
	@Column(name="username")
	private String username;
	@Column(name="LevelId")
	private int levelId;
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public int getLevelId()
	{
		return levelId;
	}
	public void setLevelId(int levelId)
	{
		this.levelId = levelId;
	}
	public RecordKey(String username, int levelId) {
		super();
		this.username = username;
		this.levelId = levelId;
	}
	public RecordKey() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
