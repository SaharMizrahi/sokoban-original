package db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="Levels")
public class Level implements Recordable
{


	@Id
	private int levelID;
	@Column(name="size")
	private int size;
	@Column(name="numberOfBox")
	private int numberOfBox;


	@Override
	public void addRecord()
	{
		// TODO Auto-generated method stub
		

	}

}
