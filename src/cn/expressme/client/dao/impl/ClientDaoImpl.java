package cn.expressme.client.dao.impl;

import java.util.List;

import javax.xml.namespace.QName;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;


import cn.expressme.other.util.HibernateUtil;
import cn.expressme.client.bean.ClientBean;
import cn.expressme.client.dao.ClientDao;

@Component
public class ClientDaoImpl implements ClientDao {

	/* (non-Javadoc)
	 * @see cn.expressme.dao.impl.ClientDao#getByPhone(java.lang.String)
	 */
	@Override
	public ClientBean getByPhone(String phone) {

		Session session = HibernateUtil.currentSession();// ����Sessionʵ��
		Criteria criteria = session.createCriteria(ClientBean.class);
		List<ClientBean> list=(List<ClientBean>) criteria.add(Restrictions.eq("phone", phone)).list();
        
		return list.size()>0?list.get(0):null;
	}

	/* (non-Javadoc)
	 * @see cn.expressme.dao.impl.ClientDao#get(java.lang.String)
	 */
	@Override
	public ClientBean load(String id) {

		return HibernateUtil.currentSession().load(ClientBean.class, id); // ����־������		
	}

	@Override
	public ClientBean insert(ClientBean bean) {
		Session session = HibernateUtil.currentSession();// ����Sessionʵ��
		Transaction tx = session.beginTransaction();
		try {

			session.save(bean); // ����־������
			tx.commit(); // �ύ�����ݿ�
			session.close();
			return bean;
		} catch (Exception e) {
			//e.printStackTrace();
			tx.rollback();
		}

		return null;
	}
	
	public ClientBean insert(String userID) {
		ClientBean clientBean = new ClientBean(userID);
		Session session = HibernateUtil.currentSession();// ����Sessionʵ��
		Transaction tx = session.beginTransaction();
		try {

			session.save(clientBean); // ����־������
			tx.commit(); // �ύ�����ݿ�
			session.close();
			return clientBean;
		} catch (Exception e) {
			//e.printStackTrace();
			tx.rollback();
		}

		return null;
	}

	/* (non-Javadoc)
	 * @see cn.expressme.dao.impl.ClientDao#update(cn.expressme.bean.ClientBean)
	 */
	@Override
	public boolean update(ClientBean clientBean) {

		Session session = HibernateUtil.currentSession();// ����Sessionʵ��
		Transaction tx = session.beginTransaction();
		try {

			session.update(clientBean); // ����־������
			tx.commit(); // �ύ�����ݿ�
			session.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see cn.expressme.dao.impl.ClientDao#delete(cn.expressme.bean.ClientBean)
	 */
	@Override
	public boolean delete(ClientBean userInfo) {

		Session session = HibernateUtil.currentSession();// ����Sessionʵ��
		Transaction tx = session.beginTransaction();
		try {

			session.delete(userInfo); // ����־������
			tx.commit(); // �ύ�����ݿ�
			session.close();
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			tx.rollback();
		}
		return false;
	}

	
}
