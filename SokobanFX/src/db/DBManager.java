package db;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
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
		try
		{
			tx = session.beginTransaction();
			session.save(o);
			tx.commit();
			System.out.println("Record added...");
		}
		catch (HibernateException e) {
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


}
