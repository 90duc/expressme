package test.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cn.expressme.client.bean.ClientVerifyBean;
import cn.expressme.client.dao.ClientVerifyDao;
import cn.expressme.client.dao.impl.ClientVerifyDaoImpl;
import cn.expressme.driver.bean.DriverBean;
import cn.expressme.driver.bean.DriverVerifyBean;
import cn.expressme.driver.dao.DriverDao;
import cn.expressme.driver.dao.DriverVerifyDao;
import cn.expressme.driver.dao.impl.DriverDaoImpl;
import cn.expressme.driver.dao.impl.DriverVerifyDaoImpl;
import cn.expressme.other.res.VerifyState;

public class DriverVerifyDaoTest {

	DriverVerifyDao dao;
	DriverVerifyBean bean;
	String id="2c90a4485a5fe30a015a5fe30faa0001";
	@Before
	public void setUp() throws Exception {
		dao=new DriverVerifyDaoImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
	public void testGet() {
		bean=dao.load(id);
		System.out.println(bean.getID());
		System.out.println(bean.getDriver().getUserName());
		System.out.println(bean.getCardPhoto0());
	}

//	@Test
	public void testInsert() {
		DriverDao cdao = new DriverDaoImpl();		
		DriverBean cbean=cdao.load(id);
		bean =new DriverVerifyBean();
		bean.setDriver(cbean);
		bean.setVerify(VerifyState.VERIFYING);
		cbean.setCity("北京东城");
		bean.setCardID("CardID");
		bean.setCardPhoto0("insert1".getBytes());
		dao.insert(bean);
	}

//	@Test
	public void testUpdate() {
		DriverDao cdao = new DriverDaoImpl();		
		DriverBean cb=cdao.load(id);
		bean=dao.load(id);
		bean.setRealName("诉苦nim");
		bean.setCardPhoto0("gbds".getBytes());
		bean.getDriver().setCity("上海123");
		dao.update(bean);
		
		System.out.println(cb.getCity());
	}

//	@Test
	public void testDelete() {
		bean=dao.load(id);
		bean.setVerify(VerifyState.NONE);
		dao.delete(bean);
	}

}
