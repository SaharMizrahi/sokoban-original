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


@Entity
public class Record implements Recordable,Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static SessionFactory factory;

	@EmbeddedId
	private RecordsKey key;
	@Column(name="time")
	private int time;
	@Column(name="steps")
	private int steps;	
	


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



	
	public Record() {
		super();
		// TODO Auto-generated constructor stub
	}




	public Record(RecordsKey key, int time, int steps) {
		super();
		this.key = key;
		this.time = time;
		this.steps = steps;
	}




	public RecordsKey getKey()
	{
		return key;
	}



	public void setKey(RecordsKey key)
	{
		this.key = key;
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
