package db;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

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
	private int levelID;
	@OneToMany
	@JoinColumn(name="levelID")
	private List<Record> levelRecords;
	@Column(name="size")
	private int size;
	@Column(name="numOfBox")
	private int numOfBox;


	public RecLevel() {
		super();
		// TODO Auto-generated constructor stub
	}


	public RecLevel(int levelID, int size, int numOfBox) {
		super();
		this.levelID = levelID;

		this.size = size;
		this.numOfBox = numOfBox;
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


	public int getNumOfBox()
	{
		return numOfBox;
	}


	public void setNumOfBox(int numOfBox)
	{
		this.numOfBox = numOfBox;
	}


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


	

	
}
