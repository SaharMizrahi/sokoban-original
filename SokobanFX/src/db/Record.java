package db;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


@Entity(name="Records")
public class Record implements Recordable,Serializable
{
	private static SessionFactory factory;

	@EmbeddedId
	private RecordKey key;
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
			session.save(this);
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



	public int getNumOfSteps()
	{
		return numOfSteps;
	}


	public void setNumOfSteps(int numOfSteps)
	{
		this.numOfSteps = numOfSteps;
	}


	public RecordKey getKey()
	{
		return key;
	}



	public void setKey(RecordKey key)
	{
		this.key = key;
	}



	public Record(RecordKey key, int numOfSteps, int time) {
		super();
		this.key = key;
		this.numOfSteps = numOfSteps;
		this.time = time;
	}



	public int getTime()
	{
		return time;
	}


	public void setTime(int time)
	{
		this.time = time;
	}



	public Record() {
		super();
		// TODO Auto-generated constructor stub
	}



	
}
