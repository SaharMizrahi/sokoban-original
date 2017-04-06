package db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity(name="Recrods")
public class Record implements Recordable
{
	@Id
	private int levelId;
	@Column(name="numOfSteps")
	private int numOfSteps;
	@Column(name="numOfSteps")







	@Override
	public void addRecord()
	{
		// TODO Auto-generated method stub

	}
}
