package db;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity(name="Members")
public class Member
{


	@Id
	private String username;
	@OneToMany
	@JoinColumn(name="username")
	private List<Record> userrecords;
	
	

	public String getUsername()
	{
		return username;
	}


	public void setUsername(String username)
	{
		this.username = username;
	}



	public Member(String username) {
		super();
		this.username = username;
		
	}


	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
