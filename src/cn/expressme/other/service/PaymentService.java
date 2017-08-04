package cn.expressme.other.service;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.expressme.other.bean.OrderInfoable;
import cn.expressme.other.bean.OrdersBean;
import cn.expressme.other.dao.OrdersDao;
import cn.expressme.other.res.OrderState;

@Component
public class PaymentService {

	@Autowired
	private OrdersDao ordersDao;
	private ArrayList<String> refundOrders = new ArrayList<>();

	/**
	 * 客户请求退款
	 * 
	 * @param order
	 * @return
	 */
	public boolean requestRefund(String order) {

		return refundOrders.add(order);
	}

	/**
	 * 退款
	 * 
	 * @param orderID
	 * @return
	 */
	public boolean refund(String orderID) {
		synchronized (orderID.intern()) {
			OrdersBean dataOrder = ordersDao.load(orderID);
			if (Objects.equals(dataOrder, ordersDao.load(orderID))
					&& Objects.equals(dataOrder.getOrderStateE(),
							OrderState.PAY_CANCEL)) {
				// 退款
				if (ordersDao.update(dataOrder)) {
					dataOrder.setOrderStateE(OrderState.PC_FINISH);
					return true;
				}
			}
		}

		return false;
	}

	public void payToDriver(OrderInfoable order) {

	}
}
