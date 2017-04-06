package db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


@Entity(name="Recrods")
public class Record implements Recordable
{
	private static SessionFactory factory;

	@Id
	private int levelId;
	private String username;
	@Column(name="NumOfSteps")
	private int numOfSteps;
	@Column(name="time")
	private int time;


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


	public int getLevelId()
	{
		return levelId;
	}


	public void setLevelId(int levelId)
	{
		this.levelId = levelId;
	}


	public String getUsername()
	{
		return username;
	}


	public void setUsername(String username)
	{
		this.username = username;
	}


	public int getNumOfSteps()
	{
		return numOfSteps;
	}


	public void setNumOfSteps(int numOfSteps)
	{
		this.numOfSteps = numOfSteps;
	}


	public int getTime()
	{
		return time;
	}


	public void setTime(int time)
	{
		this.time = time;
	}


	public Record(int levelId, String username, int numOfSteps, int time) {
		super();
		this.levelId = levelId;
		this.username = username;
		this.numOfSteps = numOfSteps;
		this.time = time;
	}


	public Record() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
