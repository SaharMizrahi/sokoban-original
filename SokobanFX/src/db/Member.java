package db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

@Entity(name="Members")
public class Member implements Recordable
{

	private static SessionFactory factory;

	@Id
	private String username;
	@Column(name="password")
	private String password;


	@Override
	public int addRecord()
	{
		// TODO Auto-generated method stub
		Configuration configuration = new Configuration();
		configuration.configure();
		factory = configuration.buildSessionFactory();
		Transaction tx = null;
		int recID = 0;
	
		Session session = factory.openSession();
		try {
			tx = session.beginTransaction();
			recID = (Integer) session.save(this);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return recID;
	}


	public String getUsername()
	{
		return username;
	}


	public void setUsername(String username)
	{
		this.username = username;
	}


	public String getPassword()
	{
		return password;
	}


	public void setPassword(String password)
	{
		this.password = password;
	}


	public Member(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}


	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
