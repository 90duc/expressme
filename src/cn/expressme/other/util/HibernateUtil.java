package cn.expressme.other.util;
/**
 * hibernate工具
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
        {   //配置文件放在classpath路径，即src目录下
            //如果hibernate的配置文件目录为hibernate.cfg.xml，则
            //Configuration config = new Configuration().configure();
            //或Configuration config = new Configuration().configure("hibernate.cfg.xml");
            //或Configuration config = new Configuration().configure("hibernate.cfg.xml");       
            //配置其他路径如下:
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
        //如果线程没有session，打开新的session
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