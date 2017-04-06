package db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity(name="Members")
public class Records implements Recordable
{
		@Id
		private String userName;
		@Column(name="password")
		private String password;


	@Override
	public void addRecord()
	{
		// TODO Auto-generated method stub

	}
}
