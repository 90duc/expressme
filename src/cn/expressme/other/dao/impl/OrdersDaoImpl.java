package cn.expressme.other.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import cn.expressme.driver.bean.DriverBean;
import cn.expressme.other.bean.OrderInfoBean;
import cn.expressme.other.bean.OrdersBean;
import cn.expressme.other.dao.OrdersDao;
import cn.expressme.other.res.OrderState;
import cn.expressme.other.util.HibernateUtil;
@SuppressWarnings({ "deprecation", "unchecked" })
@Component
public class OrdersDaoImpl implements OrdersDao {

	/* (non-Javadoc)
	 * @see cn.expressme.dao.impl.OrderDao#get(java.lang.String)
	 */
	@Override
	public OrdersBean load(String id) {
		
		return HibernateUtil.currentSession().load(OrdersBean.class, id); 
	}

	/* (non-Javadoc)
	 * @see cn.expressme.dao.impl.OrderDao#getByDriver(java.lang.String)
	 */
	@Override
	public List<OrdersBean> getByDriver(String id) {

		Session session = HibernateUtil.currentSession();// 生成Session实例
		Criteria criteria = session.createCriteria(OrdersBean.class);
		List<OrdersBean> list=(List<OrdersBean>) criteria.add(Restrictions.eq("joinOrderBean.driverBean.ID", id)).addOrder(Order.desc("placeTime")).list();
        
		return list;
	}

	/**
	 * 
	 */
	@Override
	public List<OrdersBean> getByClient(String id,int size,int pageIndex) {

		Session session = HibernateUtil.currentSession();// 生成Session实例
		
		Criteria criteria = session.createCriteria(OrdersBean.class);
		criteria.setFirstResult(pageIndex*size);
		criteria.setMaxResults(size);
		List<OrdersBean> list=(List<OrdersBean>) criteria.add(Restrictions.eq("clientBean.ID", id)).addOrder(Order.desc("placeTime")).list();
        
		return list;
	}

	/* (non-Javadoc)
	 * @see cn.expressme.dao.impl.OrderDao#insert(cn.expressme.bean.OrderBean)
	 */
	@Override
	public boolean insert(OrdersBean order) {

		Session session = HibernateUtil.currentSession();// 生成Session实例
		Transaction tx = session.beginTransaction();
		try {

			session.save(order); // 保存持久类对象
			tx.commit(); // 提交到数据库
			session.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}

		return false;
	}

	/* (non-Javadoc)
	 * @see cn.expressme.dao.impl.OrderDao#update(cn.expressme.bean.OrderBean)
	 */
	@Override
	public boolean update(OrdersBean order) {
		Session session = HibernateUtil.currentSession();// 生成Session实例
		Transaction tx = session.beginTransaction();
		try {

			session.update(order); // 保存持久类对象
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
	 * @see cn.expressme.dao.impl.OrderDao#delete(cn.expressme.bean.OrderBean)
	 */
	@Override
	public boolean delete(OrdersBean order) {
		Session session = HibernateUtil.currentSession();// 生成Session实例
		Transaction tx = session.beginTransaction();
		try {

			session.delete(order); // 保存持久类对象
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
	 * @see cn.expressme.dao.impl.OrderDao#getWithoutReceive()
	 */
	@Override
	public List<OrdersBean> getWithoutReceive() {
		Session session = HibernateUtil.currentSession();// 生成Session实例
		Criteria criteria = session.createCriteria(OrdersBean.class);
		List<OrdersBean> list=(List<OrdersBean>) criteria.add(Restrictions.eq("orderState", OrderState.PAYED)).addOrder(Order.asc("placeTime")).list();
		//List<OrderBean> list=(List<OrderBean>) criteria.add(Restrictions.isNull("driverBean")).list();
        
		return list;		
	}
}
