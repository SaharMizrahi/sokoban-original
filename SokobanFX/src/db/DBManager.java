package db;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

/*This class managed the data base activity
 * */
public class DBManager implements DBManagerInterface
{
	private static SessionFactory factory;

	public List<Record> sortRecordsByTime(int num)
	{
		Configuration configuration = new Configuration();
		configuration.configure();
		factory = configuration.buildSessionFactory();
		Session session = factory.openSession();
		Query query = session.createQuery("FROM Records E " + "ORDER BY E.time ASC");
		query.setMaxResults(num);
		// session.close();

		return query.list();

	}

	public List sortRecordsBySteps(int num)
	{
		Configuration configuration = new Configuration();
		configuration.configure();
		factory = configuration.buildSessionFactory();
		Session session = factory.openSession();
		Query query = session.createQuery("FROM Records E " + "ORDER BY E.steps ASC");
		query.setMaxResults(num);
		// session.close();

		return query.list();

	}

	public void addRecord(Object o)
	{

		Configuration configuration = new Configuration();
		configuration.configure();
		factory = configuration.buildSessionFactory();
		Transaction tx = null;
		int recID = 0;
		Session session = factory.openSession();
		try {
			tx = session.beginTransaction();
			session.save(o);
			tx.commit();
			System.out.println("Record added...");
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	public void deleteRecord(Object o)
	{

	}

	@Override
	public List<Record> showAllRecords()
	{
		// TODO Auto-generated method stub
		Configuration configuration = new Configuration();
		configuration.configure();
		factory = configuration.buildSessionFactory();
		Session session = factory.openSession();
		Query query = session.createQuery("FROM Records E");

		return query.list();
	}

	@Override
	public List<Record> showLevelRecords(int levelID)
	{
		// TODO Auto-generated method stub
		Configuration configuration = new Configuration();
		configuration.configure();
		factory = configuration.buildSessionFactory();
		Session session = factory.openSession();
		Query query = session.createQuery("FROM Records WHERE levelID =  " + levelID);

		return query.list();

	}

	@Override
	public List<Record> showRecordsByMember(String username)
	{
		// TODO Auto-generated method stub
		Configuration configuration = new Configuration();
		configuration.configure();
		factory = configuration.buildSessionFactory();
		Session session = factory.openSession();
		Query query = session.createQuery("SELECT levelID,time,steps FROM Records WHERE username =  " + username);

		return query.list();

	}


	public List<Record> showTop(int levelID, String sortArg, int numOfRec)
	{

		// TODO Auto-generated method stub
		Configuration configuration = new Configuration();
		configuration.configure();
		factory = configuration.buildSessionFactory();
		Session session = factory.openSession();
		Query query = session
				.createQuery("FROM Records E WHERE levelID = " + levelID + " ORDER BY E." + sortArg + " ASC");
		query.setMaxResults(numOfRec);
		return query.list();
	}

}
