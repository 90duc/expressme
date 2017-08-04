package test.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cn.expressme.client.bean.ClientBean;
import cn.expressme.client.dao.ClientDao;
import cn.expressme.client.dao.impl.ClientDaoImpl;

public class ClientDaoTest {
	ClientDao dao;
	ClientBean bean;
	@Before
	public void setUp() throws Exception {
		dao=new ClientDaoImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetByPhone() {
		
	}

	@Test
	public void testGet() {
		
	}

	//@Test
	public void testInsert() {
	     bean =new ClientBean();
	     bean.setAvgRang(10.92f);
	     dao.insert(bean);
	}

	@Test
	public void testUpdate() {
		
	}

	@Test
	public void testDelete() {
	
	}

}
