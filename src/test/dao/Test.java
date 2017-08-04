package test.dao;


import java.util.Date;

import cn.expressme.client.bean.ClientBean;
import cn.expressme.client.bean.ClientVerifyBean;
import cn.expressme.client.dao.impl.ClientDaoImpl;
import cn.expressme.other.bean.OrderInfoBean;
import cn.expressme.other.bean.OrdersBean;
import cn.expressme.other.dao.impl.OrdersDaoImpl;
import cn.expressme.other.res.OrderState;
import cn.expressme.other.res.Time;
import cn.expressme.other.util.Util;


public class Test {
	
	public static void main(String[] args) {
        OrdersBean order=new OrdersBean();
		OrderInfoBean orderInfoBean=new OrderInfoBean();
		//order.setOrderInfoBean(orderInfoBean);
		orderInfoBean.setOrdersBean(order);
	    OrdersDaoImpl ordersDaoImpl=new OrdersDaoImpl();
	    ordersDaoImpl.insert(order);
		/*ClientBean clientBean=new ClientBean("13423542231");
		ClientVerifyBean clientVerifyBean=new ClientVerifyBean();
		//clientBean.setVerifyBean(clientVerifyBean);
		clientVerifyBean.setClient(clientBean);
		ClientDaoImpl clientDaoImpl=new ClientDaoImpl();
		clientDaoImpl.insert(clientBean);*/
	
	}
}
