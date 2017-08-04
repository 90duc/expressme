package cn.expressme.driver.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import cn.expressme.client.bean.ClientBean;
import cn.expressme.driver.bean.DriverBean;
import cn.expressme.driver.dao.DriverDao;
import cn.expressme.other.util.HibernateUtil;

@Component
public class DriverDaoImpl implements DriverDao {
     
	/* (non-Javadoc)
	 * @see cn.expressme.dao.impl.DriverDao#getByPhone(java.lang.String)
	 */
	@Override
	public DriverBean getByPhone(String phone) {
		Session session = HibernateUtil.currentSession();// 生成Session实例
		Criteria criteria = session.createCriteria(DriverBean.class);
		List<DriverBean> list=(List<DriverBean>) criteria.add(Restrictions.eq("phone", phone)).list();
        
		return list.size()>0?list.get(0):null;
	}
	
   /* (non-Javadoc)
 * @see cn.expressme.dao.impl.DriverDao#get(java.lang.String)
 */
@Override
public DriverBean load(String id) {
		
		
		return HibernateUtil.currentSession().load(DriverBean.class, id);
	}
   

	/* (non-Javadoc)
	 * @see cn.expressme.dao.impl.DriverDao#insert(java.lang.String)
	 */
	@Override
	public DriverBean insert(String userID) {
		DriverBean driverBean = new DriverBean(userID);
		Session session = HibernateUtil.currentSession();// 生成Session实例
		Transaction tx = session.beginTransaction();
		try {

			session.save(driverBean); // 保存持久类对象
			tx.commit(); // 提交到数据库
			session.close();
			return driverBean;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}

		return null;
	}

	/* (non-Javadoc)
	 * @see cn.expressme.dao.impl.DriverDao#update(cn.expressme.bean.DriverBean)
	 */
	@Override
	public boolean update(DriverBean driverBean) {
		Session session = HibernateUtil.currentSession();// 生成Session实例
		Transaction tx = session.beginTransaction();
		try {

			session.update(driverBean); // 保存持久类对象
			tx.commit(); // 提交到数据库
			session.close();
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			tx.rollback();
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * @see cn.expressme.dao.impl.DriverDao#delete(cn.expressme.bean.DriverBean)
	 */
	@Override
	public boolean delete(DriverBean driverBean) {

		Session session = HibernateUtil.currentSession();// 生成Session实例
		Transaction tx = session.beginTransaction();
		try {

			session.delete(driverBean); // 保存持久类对象
			tx.commit(); // 提交到数据库
			session.close();
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			tx.rollback();
		}
		return false;
	}
}
