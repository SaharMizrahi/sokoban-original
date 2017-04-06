package db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

@Entity(name="Levels")
public class RecLevel implements Recordable
{


	private static SessionFactory factory;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int levelID;
	@Column(name="size")
	private int size;
	@Column(name="numberOfBox")
	private int numberOfBox;


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


	public RecLevel(int levelID, int size, int numberOfBox) {
		super();
		this.levelID = levelID;
		this.size = size;
		this.numberOfBox = numberOfBox;
	}


	public int getLevelID()
	{
		return levelID;
	}


	public void setLevelID(int levelID)
	{
		this.levelID = levelID;
	}


	public int getSize()
	{
		return size;
	}


	public void setSize(int size)
	{
		this.size = size;
	}


	public int getNumberOfBox()
	{
		return numberOfBox;
	}


	public void setNumberOfBox(int numberOfBox)
	{
		this.numberOfBox = numberOfBox;
	}


	public RecLevel() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
