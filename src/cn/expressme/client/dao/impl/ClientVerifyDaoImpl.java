package cn.expressme.client.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import cn.expressme.client.bean.ClientVerifyBean;
import cn.expressme.client.dao.ClientVerifyDao;
import cn.expressme.other.bean.OrderInfoBean;
import cn.expressme.other.util.HibernateUtil;

@Component
public class ClientVerifyDaoImpl implements ClientVerifyDao {
	/* (non-Javadoc)
	 * @see cn.expressme.dao.impl.ClientVerifyDao#get(java.lang.String)
	 */
	@Override
	public ClientVerifyBean load(String id) {
		
		return HibernateUtil.currentSession().load(ClientVerifyBean.class, id);
		
	}
	
   
   /* (non-Javadoc)
 * @see cn.expressme.dao.impl.ClientVerifyDao#insert(cn.expressme.bean.ClientVerifyBean)
 */
@Override
public boolean insert(ClientVerifyBean verify) {
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
 * @see cn.expressme.dao.impl.ClientVerifyDao#update(cn.expressme.bean.ClientVerifyBean)
 */
@Override
public boolean update(ClientVerifyBean verify) {
		
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
 * @see cn.expressme.dao.impl.ClientVerifyDao#delete(cn.expressme.bean.ClientVerifyBean)
 */
@Override
public boolean delete(ClientVerifyBean verify) {
		
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
