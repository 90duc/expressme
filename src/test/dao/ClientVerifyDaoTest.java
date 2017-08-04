package test.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cn.expressme.client.bean.ClientBean;
import cn.expressme.client.bean.ClientVerifyBean;
import cn.expressme.client.dao.ClientDao;
import cn.expressme.client.dao.ClientVerifyDao;
import cn.expressme.client.dao.impl.ClientDaoImpl;
import cn.expressme.client.dao.impl.ClientVerifyDaoImpl;
import cn.expressme.other.res.VerifyState;

public class ClientVerifyDaoTest {

	ClientVerifyDao dao;
	ClientVerifyBean bean;
	String id="2c90a4485a5fdc07015a5fdc0b2d0000";
	@Before
	public void setUp() throws Exception {
		dao=new ClientVerifyDaoImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
	public void testGet() {
		bean=dao.load(id);
		System.out.println(bean.getID());
		System.out.println(bean.getClient().getUserName());
		System.out.println(bean.getCardPhoto0());
	}

//	@Test
	public void testInsert() {
		ClientDao cdao = new ClientDaoImpl();		
		ClientBean clientBean=cdao.load(id);
		bean =new ClientVerifyBean();
		bean.setClient(clientBean);
		//bean.setVerify(VerifyState.VERIFYING);
		clientBean.setCity("北京东城");
		bean.setCardID("CardID");
		bean.setCardPhoto0("insert1");
		dao.insert(bean);
	}

//	@Test
	public void testUpdate() {
		
		bean=dao.load(id);
		bean.setRealName("诉苦");
		bean.setCardPhoto0("gbds");
		bean.getClient().setCity("上海");
		dao.update(bean);
		ClientDao cdao = new ClientDaoImpl();		
		ClientBean cb=cdao.load(id);
		System.out.println(cb.getCity());
	}

//	@Test
	public void testDelete() {
		bean=dao.load(id);
		//bean.setVerify(VerifyState.NONE);
		dao.delete(bean);
	}

}
