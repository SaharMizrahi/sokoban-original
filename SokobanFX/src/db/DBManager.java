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
		Configuration configuration = new Configuration();
		configuration.configure();
		factory = configuration.buildSessionFactory();
		Transaction tx = null;
		int recID = 0;
		Session session = factory.openSession();
		try {
			tx = session.beginTransaction();
			session.delete(o);
			tx.commit();
			System.out.println("Record deleted...");
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
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


	public List<Record> showTop(String argType,String arg, String sortArg, String numOfRec)
	{

		// TODO Auto-generated method stub
		Configuration configuration = new Configuration();
		configuration.configure();
		factory = configuration.buildSessionFactory();
		Session session = factory.openSession();
		Query query;
		if(argType=="username")
		{
			query = session.createQuery("FROM Records E WHERE "+argType+" LIKE '"+arg + "' ORDER BY E." + sortArg + " ASC");

		}
		else
		{
			query = session.createQuery("FROM Records E WHERE "+argType+" = "+arg + " ORDER BY E." + sortArg + " ASC");

		}
		query.setMaxResults(Integer.parseInt(numOfRec));
		return query.list();
	}



	@Override
	public List<Record> showRecordsByLevelID(int levelID)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isMemberExist(String username)
	{
		// TODO Auto-generated method stub
		Configuration configuration = new Configuration();
		configuration.configure();
		factory = configuration.buildSessionFactory();
		Session session = factory.openSession();
		Query query = session.createQuery("FROM Members E WHERE username LIKE '"+username+"'" );
		if(query.list()==null)
			return false;
		return true;
	}

	@Override
	public boolean isLevelExist(int levelID)
	{
		// TODO Auto-generated method stub
		Configuration configuration = new Configuration();
		configuration.configure();
		factory = configuration.buildSessionFactory();
		Session session = factory.openSession();
		Query query = session.createQuery("FROM Levels E WHERE levelID = "+levelID );
		if(query.list().size()==0)
			return false;
		return true;
	}

	@Override
	public boolean isRecordExist(String username, int levelID)
	{
		// TODO Auto-generated method stub
		Configuration configuration = new Configuration();
		configuration.configure();
		factory = configuration.buildSessionFactory();
		Session session = factory.openSession();
		Query query = session.createQuery("FROM Records E WHERE username LIKE '"+username+"' AND levelID = "+levelID );
		if(query.list().size()==0)
			return false;
		return true;

	}

	@Override
	public Object findRecord(String username, int levelID)
	{
		// TODO Auto-generated method stub
		if(this.isRecordExist(username, levelID))
		{
			Configuration configuration = new Configuration();
			configuration.configure();
			factory = configuration.buildSessionFactory();
			Session session = factory.openSession();
			Query query = session.createQuery("FROM Records E WHERE username LIKE '"+username+"' AND levelID = "+levelID );
			return query.list().get(0);
		}
		return null;
	}


}
