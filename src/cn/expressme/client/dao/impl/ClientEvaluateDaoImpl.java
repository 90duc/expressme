package cn.expressme.client.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import cn.expressme.client.bean.ClientBean;
import cn.expressme.client.bean.ClientEvaluateBean;
import cn.expressme.client.dao.ClientEvaluateDao;
import cn.expressme.other.util.HibernateUtil;
@Component
public class ClientEvaluateDaoImpl implements ClientEvaluateDao {

	/* (non-Javadoc)
	 * @see cn.expressme.dao.impl.EvaluateDao#get(java.lang.String)
	 */
	@Override
	public ClientEvaluateBean load(String id) {

		return HibernateUtil.currentSession().load(ClientEvaluateBean.class, id);
	}

	@SuppressWarnings({"deprecation","unchecked"})
	@Override
	public List<ClientEvaluateBean> getByDriverID(String id,int size,int pageIndex) {
		
		String sql ="select e.* from clientEvaluate as e ,orderbill, joinorder where e.id=orderbill.id and orderbill.joinorderid =joinorder.id and joinorder.driverID=? limit ?, ?";
		Session session = HibernateUtil.currentSession();// 生成Session实例
		Query<ClientEvaluateBean> query = (Query<ClientEvaluateBean>)session.createSQLQuery(sql);
		query.setParameter(0, id);
		query.setParameter(1, pageIndex);
		query.setParameter(2, size);
		List<ClientEvaluateBean> list=(List<ClientEvaluateBean>) query.list();        
		return list;
	}

	
	/* (non-Javadoc)
	 * @see cn.expressme.dao.impl.EvaluateDao#getAvgRange(java.lang.String)
	 */
	@Override
	@SuppressWarnings({"deprecation","unchecked"})
	public double getAvgRange(String evaluated) {

		String sql ="select avg(e.mark) from clientEvaluate as e where e.driverID=?";
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
	public boolean insert(ClientEvaluateBean evaluate) {

		
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
	public boolean update(ClientEvaluateBean evaluate) {
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
	public boolean delete(ClientEvaluateBean evaluate) {

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
