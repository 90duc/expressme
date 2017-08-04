package test.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cn.expressme.client.bean.ClientEvaluateBean;
import cn.expressme.client.dao.ClientEvaluateDao;
import cn.expressme.client.dao.impl.ClientEvaluateDaoImpl;
import cn.expressme.other.bean.OrderInfoable;
import cn.expressme.other.bean.OrdersBean;
import cn.expressme.other.dao.OrdersDao;
import cn.expressme.other.dao.impl.OrdersDaoImpl;

public class ClientEvaluateDaoTest {

	ClientEvaluateDao dao;
	String id="2c90a4485a603583015a603588fe0000";
	ClientEvaluateBean bean;
	
	@Before
	public void setUp() throws Exception {
		dao=new ClientEvaluateDaoImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
	public void testGet() {
		 bean=dao.load(id);
         System.out.println(bean.getOrder().getClientID());
         System.out.println(bean.getDriverID());
		
	}
	
	

//	@Test
	public void testGetAvgRange() {
		System.out.println(dao.getAvgRange("2c90a4485a5fe30a015a5fe30faa0001"));

	}
	
	

//	@Test
	public void testInsert() {
		  OrdersDao ordersDao=new OrdersDaoImpl();
		  OrdersBean order=ordersDao.load(id);
          bean=new ClientEvaluateBean();
          bean.setOrder(order);
          dao.insert(bean);
	}

//	@Test
	public void testUpdate() {
		 bean=dao.load(id);
         bean.setText("≤Ó∆¿123");
         dao.update(bean);
       System.out.println(bean.getDriverID());

	}
	
//	@Test
	public void testDelete() {
		bean=dao.load(id);
		dao.delete(bean);

	}

}
