package cn.expressme.driver.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import cn.expressme.client.bean.ClientEvaluateBean;
import cn.expressme.client.dao.ClientEvaluateDao;
import cn.expressme.driver.bean.DriverEvaluateBean;
import cn.expressme.driver.dao.DriverEvaluateDao;
import cn.expressme.other.util.HibernateUtil;

public class DriverEvaluateDaoImpl implements DriverEvaluateDao {

	/* (non-Javadoc)
	 * @see cn.expressme.dao.impl.EvaluateDao#get(java.lang.String)
	 */
	@Override
	public DriverEvaluateBean load(String id) {

		return HibernateUtil.currentSession().load(DriverEvaluateBean.class, id);
	}

	
	/* (non-Javadoc)
	 * @see cn.expressme.dao.impl.EvaluateDao#getAvgRange(java.lang.String)
	 */
	@Override
	@SuppressWarnings({"deprecation","unchecked"})
	public double getAvgRange(String evaluated) {

		String sql ="select avg(e.mark) from driverEvaluate as e where e.clientID=?";
		Session session = HibernateUtil.currentSession();// 生成Session实例
		Query<Double> query = (Query<Double>)session.createSQLQuery(sql);
		query.setParameter(0, evaluated);
		
		
		Double o=query.uniqueResult();
		return o==null?0:(double)o;

	}
	
	/* (non-Javadoc)
	 * @see cn.expressme.dao.impl.EvaluateDao#insert(cn.expressme.bean.EvaluateBean)
	 */
	@Override
	public boolean insert(DriverEvaluateBean evaluate) {

		
		Session session = HibernateUtil.currentSession();// 生成Session实例
		Transaction tx = session.beginTransaction();
		try {

			session.save(evaluate); // 保存持久类对象
			tx.commit(); // 提交到数据库
			//session.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}

		return false;
	}
	

	@Override
	public boolean update(DriverEvaluateBean evaluate) {
		Session session = HibernateUtil.currentSession();// 生成Session实例
		Transaction tx = session.beginTransaction();
		try {

			session.update(evaluate); // 保存持久类对象
			tx.commit(); // 提交到数据库
			//session.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}

		return false;
	}

	/* (non-Javadoc)
	 * @see cn.expressme.dao.impl.EvaluateDao#delete(cn.expressme.bean.EvaluateBean)
	 */
	@Override
	public boolean delete(DriverEvaluateBean evaluate) {

		Session session = HibernateUtil.currentSession();// 生成Session实例
		Transaction tx = session.beginTransaction();
		try {

			session.delete(evaluate); // 保存持久类对象
			tx.commit(); // 提交到数据库
			//session.close();
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			tx.rollback();
		}
		return false;
	}


}
