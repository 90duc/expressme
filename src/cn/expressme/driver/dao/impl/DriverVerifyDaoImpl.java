package cn.expressme.driver.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import cn.expressme.client.bean.ClientVerifyBean;
import cn.expressme.driver.bean.DriverVerifyBean;
import cn.expressme.driver.dao.DriverVerifyDao;
import cn.expressme.other.bean.OrderInfoBean;
import cn.expressme.other.util.HibernateUtil;

@Component
public class DriverVerifyDaoImpl implements DriverVerifyDao {
	
	/* (non-Javadoc)
	 * @see cn.expressme.dao.impl.DriverVerifyDao#get(java.lang.String)
	 */
	@Override
	public DriverVerifyBean load(String id) {
		
		return HibernateUtil.currentSession().load(DriverVerifyBean.class, id);
		
	}
	
   
   /* (non-Javadoc)
 * @see cn.expressme.dao.impl.DriverVerifyDao#insert(cn.expressme.bean.DriverVerifyBean)
 */
@Override
public boolean insert(DriverVerifyBean verify) {
		
	   Session session = HibernateUtil.currentSession();// ����Sessionʵ��
		Transaction tx = session.beginTransaction();
		try {

			session.save(verify); // ����־������
			tx.commit(); // �ύ�����ݿ�
			//session.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}

		return false;
	}
   /* (non-Javadoc)
 * @see cn.expressme.dao.impl.DriverVerifyDao#update(cn.expressme.bean.DriverVerifyBean)
 */
@Override
public boolean update(DriverVerifyBean verify) {
	   Session session = HibernateUtil.currentSession();// ����Sessionʵ��
		Transaction tx = session.beginTransaction();
		try {

			session.update(verify); // ����־������
			tx.commit(); // �ύ�����ݿ�
			//session.close();
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			tx.rollback();
		}
		return false;	
	}
   
   /* (non-Javadoc)
 * @see cn.expressme.dao.impl.DriverVerifyDao#delete(cn.expressme.bean.DriverVerifyBean)
 */
@Override
public boolean delete(DriverVerifyBean verify) {
		
	   Session session = HibernateUtil.currentSession();// ����Sessionʵ��
		Transaction tx = session.beginTransaction();
		try {

			session.delete(verify); // ����־������
			tx.commit(); // �ύ�����ݿ�
			//session.close();
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			tx.rollback();
		}
		return false;	
	}
}
