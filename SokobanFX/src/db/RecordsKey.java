package db;

import java.io.Serializable;

import javax.persistence.Embeddable;
@Embeddable
public class RecordsKey implements Serializable
{

	private String username;
	private int levelID;
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + levelID;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecordsKey other = (RecordsKey) obj;
		if (levelID != other.levelID)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
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
