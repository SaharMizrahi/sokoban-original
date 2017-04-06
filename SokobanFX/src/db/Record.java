package db;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;


@Entity(name="Records")
@IdClass(RecordsKey.class)
public class Record implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	
	@Id
	private String username;
	@Id
	private int levelID;
	
	@Column(name="time")
	private int time;
	@Column(name="steps")
	private int steps;	
	





	
	public Record() {
		super();
		// TODO Auto-generated constructor stub
	}




	public Record(RecordsKey key, int time, int steps) {
		super();
		this.levelID=key.getLevelID();
		this.username=key.getUsername();
		this.time = time;
		this.steps = steps;
	}







	public int getTime()
	{
		return time;
	}



	public void setTime(int time)
	{
		this.time = time;
	}



	public int getSteps()
	{
		return steps;
	}



	public void setSteps(int steps)
	{
		this.steps = steps;
	}



	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}
	


		
}
