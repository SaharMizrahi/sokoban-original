package db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity(name="Recrods")
public class Record implements Recordable
{
	@Id
	private int levelId;
	private String username;
	@Column(name="NumOfSteps")
	private int numOfSteps;
	@Column(name="time")
	private int time;


	@Override
	public void addRecord()
	{
		// TODO Auto-generated method stub

	}
}
