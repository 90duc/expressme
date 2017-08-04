package cn.expressme.other.service;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.expressme.other.bean.OrderInfoBean;
import cn.expressme.other.bean.OrderInfoable;
import cn.expressme.other.dao.OrdersDao;
import cn.expressme.other.res.FinalInfo;

@Component
public class OrderService {

	public static final long PAY_LIMIT_TIME=30*60*1000;
	private ArrayList<OrderInfoBean> orders= new ArrayList<>();
	
	@Autowired
	private OrdersDao ordersDao;
	@Autowired
	private HttpSession session;
	@Autowired
	private PaymentService payment;
	
	
//	public Order getOrder() {
//		
//		return (Order)session.getAttribute(FinalStringInfo.ORDER);
//	}
	
    public OrderInfoable getOrder(String billNumber) {
		
		return (OrderInfoable)session.getAttribute(FinalInfo.ORDER);
	}
//
//    public boolean cancelOrder(OrderBean order,UserBean userInfo) {
//    	
//    	boolean result=true;
//    	
//    	synchronized (order) {
//			switch (order.getOrderStateE()) {
//
//			case NONE:
//				order.setOrderStateE(OrderState.CLOSE);			
//				break;
//
//			case PAYED:
//				order.setOrderStateE(OrderState.PAY_CANCEL);
//				payment.requestRefund(order);
//				break;
//			case RECIEVE:
//				
//                if(order.getDriverID().equals(userInfo.getID())){
//                	order.setOrderStateE(OrderState.PAYED);
//                }else  if(order.getClientID().equals(userInfo.getID())){
//                	order.setOrderStateE(OrderState.PAY_CANCEL);
//                	payment.requestRefund(order);
//                }else {
//					result =false;
//				}
//				
//				break;
//				
//			case DELIVERY:
//				if (order.getDriverID().equals(userInfo.getID())) {
//					order.setOrderStateE(OrderState.DD_CANCEL);
//					//请求对方同意处理
//					//然后退款处理
//				} else if(order.getClientID().equals(userInfo.getID())){
//					order.setOrderStateE(OrderState.CD_CANCEL);
//					//请求对方同意处理
//					//然后退款处理
//				} else {
//					result =false;
//				}
//				
//				break;
//		
//			default:
//				result=false;
//				break;
//
//			}
//
//		}
//
//       return result;
//	 }
	public OrdersDao getOrderDao() {
		return ordersDao;
	}

	public HttpSession getSession() {
		return session;
	}
	
	
}
