package cn.expressme.other.util;
/**
 * hibernate����
 * @author maokun
 * 
 */
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    
    private static final SessionFactory sessionFactory;
    
    static
    {
        try
        {   //�����ļ�����classpath·������srcĿ¼��
            //���hibernate�������ļ�Ŀ¼Ϊhibernate.cfg.xml����
            //Configuration config = new Configuration().configure();
            //��Configuration config = new Configuration().configure("hibernate.cfg.xml");
            //��Configuration config = new Configuration().configure("hibernate.cfg.xml");       
            //��������·������:
            //Configuration config = new Configuration().configure("hibernate/hibernate.cfg.xml");

            Configuration config = new Configuration().configure("/cn/expressme/other/util/hibernate.cfg.xml");
            sessionFactory = config.buildSessionFactory();
        }
        catch(Throwable e)
        {
            throw new ExceptionInInitializerError(e);
        }
    }
    
    public static final ThreadLocal<Session> session = new ThreadLocal<>();
    
    public static Session currentSession() throws HibernateException
    {
        Session s = session.get();
        //����߳�û��session�����µ�session
        if(s == null || !s.isOpen())
        {
            s = sessionFactory.openSession();
            session.set(s);
        }
        return s;
    }
    
    public static void closeSession() throws HibernateException
    {
        Session s =session.get();
        session.set(null);
        if(s != null)
            s.close();
    }

}