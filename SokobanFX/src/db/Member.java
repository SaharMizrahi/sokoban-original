package db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="Members")
public class Member implements Recordable
{

	@Id
	private String username;
	@Column(name="password")
	private String password;


	@Override
	public void addRecord()
	{
		// TODO Auto-generated method stub

	}

}
