package cn.expressme.client.action;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.expressme.client.bean.ClientBean;
import cn.expressme.client.bean.ClientEvaluateBean;
import cn.expressme.client.dao.ClientEvaluateDao;
import cn.expressme.driver.bean.DriverBean;
import cn.expressme.other.bean.JoinOrderBean;
import cn.expressme.other.bean.OrdersBean;
import cn.expressme.other.bean.OrdersBean;
import cn.expressme.other.bean.PaymentBean;
import cn.expressme.other.dao.OrdersDao;
import cn.expressme.other.res.FinalInfo;
import cn.expressme.other.res.JoinOrderState;
import cn.expressme.other.res.OrderState;
import cn.expressme.other.res.Time;
import cn.expressme.other.service.OrderService;
import cn.expressme.other.service.PaymentService;
import cn.expressme.other.service.UserService;

@Controller
@RequestMapping("/client")
public class ClientOrderAtion {

	@Autowired
	private OrderService orderService;
	@Autowired
	private OrdersDao ordersDao;
	// @Autowired
	// private UserInfoDao userInfoDao;
	@Autowired
	private UserService userInfoService;

	@Autowired
	private PaymentService payment;

	/**
	 * ��ѯ����˾������Ϣ
	 * 
	 * @param billNumber
	 * @return
	 */
	@RequestMapping("/driverinfo.xhtm")
	public ModelAndView showDriverInfo(String id) {

		DriverBean userInfo = userInfoService.getDriverDao().load(id);
		List<ClientEvaluateBean> list = evaluateDao.getByDriverID(id,
				Integer.MAX_VALUE, 0);
		ModelAndView mv = new ModelAndView();
		mv.addObject(FinalInfo.DRIVER_INFO, userInfo);
		mv.addObject(FinalInfo.EVALUATES, list);
		mv.setViewName("driverInfo.jsp");
		return mv;
	}

	private static final String orderInfoUrl = "orderinfo.xhtm";

	/**
	 * �ͻ���������
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(orderInfoUrl)
	public ModelAndView showClientOrderInfo(String id) {

		OrdersBean order = ordersDao.load(id);
		ModelAndView mv = new ModelAndView();
		mv.addObject(FinalInfo.ORDER, order);
		String path = "order_unpay.jsp";
		long time;
		Time date;
		switch (order.getOrderStateE()) {
		case NONE:
			time = OrderService.PAY_LIMIT_TIME
					- (System.currentTimeMillis() - order.getPlaceTimeD()
							.getTime());
			if (time > 0) {
				date = new Time(time);
				mv.addObject("time", date);
				path = "order_unpay.jsp";

				break;
			}
			order.setOrderState(OrderState.CLOSE);
			order.setFinishTime(new Date(order.getPlaceTimeD().getTime()
					+ OrderService.PAY_LIMIT_TIME));
			ordersDao.update(order);
		case CANCEL_CLOSE:
		case CLOSE:
		case PC_FINISH:
			path = "order_closed.jsp";
			break;
		case PAYED:
			time = System.currentTimeMillis()
					- order.getPaymentBean().getPayTime().getTime();
			date = new Time(time);
			mv.addObject("time", date);
			path = "order_pay.jsp";
			break;
		case PAY_CANCEL:
			time = System.currentTimeMillis()
					- order.getFinishTimeD().getTime();
			date = new Time(time);
			mv.addObject("time", date);
			path = "order_refund.jsp";
			break;
		case CD_CANCELING:
			path = "order_client_cancel.jsp";
			time = System.currentTimeMillis()
					- order.getFinishTimeD().getTime();
			date = new Time(time);
			mv.addObject("time", date);

			break;
		case CD_CANCELED:
		case DD_CANCELED:
			path = "order_delivery_canceled.jsp";
			time = System.currentTimeMillis()
					- order.getFinishTimeD().getTime();
			date = new Time(time);
			mv.addObject("time", date);

			break;
		case RECIEVE:
			path = "order_received.jsp";
			break;
		case DELIVERY:
			path = "order_delivery.jsp";
			break;
		case DD_CANCELING:
			path = "order_driver_cancel.jsp";
			time = System.currentTimeMillis()
					- order.getFinishTimeD().getTime();
			Time CDdate = new Time(time);
			mv.addObject("time", CDdate);
			break;

		case FINISH:
		case FINISH_EVALUATE:
			path = "order_finished.jsp";
			break;

		default:
			break;
		}

		mv.setViewName(path);

		return mv;
	}

	/**
	 * �ͻ������б�
	 * 
	 * @return
	 */
	@RequestMapping("/showorders.xhtm")
	public ModelAndView showClientOrders() {
		ClientBean userInfo = userInfoService.getClientBean();
		List<OrdersBean> orders = ordersDao.getByClient(userInfo.getID(),
				Integer.MAX_VALUE, 0);

		ModelAndView mView = new ModelAndView();
		mView.addObject(FinalInfo.ORDERS, orders);
		mView.setViewName("showOrders.jsp");
		return mView;
	}

	/**
	 * ֧������
	 * 
	 * @param id
	 */
	@ResponseBody
	@RequestMapping("/payment.do")
	public String payment(String id, String value) {
		// ModelAndView mv = new ModelAndView();
		// mv.addObject("billNumber", billNumber);
		// ���е�֧����Ϣ

		// mv.setViewName("payment.jsp");

		// return mv;
		String a = "no";
		if (value.equalsIgnoreCase("pay"))
			synchronized (id.intern()) {
				OrdersBean order = ordersDao.load(id);
				if (order.getOrderStateE().equals(OrderState.NONE)) {
					order.setOrderState(OrderState.PAYED);
					PaymentBean paymentBean = new PaymentBean();
					paymentBean.setOrdersBean(order);
					order.setPaymentBean(paymentBean);
					if (ordersDao.update(order))
						a = "yes";
				}
			}
		return a;
	}

	/**
	 * ȡ����������
	 * 
	 * @param id
	 */
	@RequestMapping("/cancelorder.do")
	public String clientCancelCancelingOrder(String id, String state) {

		OrderState os = OrderState.valueOf(state);
		String path = "redirect:" + orderInfoUrl + "?id=" + id;
		synchronized (id.intern()) {

			OrdersBean order = ordersDao.load(id);
			ClientBean userInfo = userInfoService.getClientBean();
			// �ǿͻ�
			if (!userInfo.getID().equals(order.getClientID()))
				return null;

			OrderState orderState = order.getOrderStateE();
			if (os.equals(orderState)) {
				switch (order.getOrderStateE()) {

				case NONE: // ȡ���µ�
					orderState = OrderState.CANCEL_CLOSE;
					break;

				case RECIEVE: // ȡ���ӵ����˿�
					JoinOrderBean joinOrderBean = order.getJoinOrder();
					joinOrderBean.setFinishTime(new Date());
					joinOrderBean.setJoinState(JoinOrderState.CR_CANCEL);

				case PAYED: // ȡ���������˿�
					orderState = OrderState.PAY_CANCEL;
					payment.requestRefund(order.getID());
					break;

				case PAY_CANCEL: // �����˿�
					orderState = OrderState.PAYED;
					break;

				case DELIVERY: // �ͻ�ȡ�����ͣ������˻�
					orderState = OrderState.CD_CANCELING;
					break;

				case CD_CANCELING: // �����ͻ�����ȡ��
					orderState = OrderState.DELIVERY;
				break;

				case DD_CANCELING: // ˾������ȡ�����ͣ��ͻ��ܾ�
					orderState = OrderState.DD_CANCELED;
					break;
				default:
					return path;
				}
				order.setOrderState(orderState);
				order.setFinishTime(new Date());
				ordersDao.update(order);

			}
		}

		return path;
	}

	/**
	 * ȷ�϶�������
	 * 
	 * @param id
	 */
	@RequestMapping("/confirmorder.do")
	public String clientConfirmOrder(String id, String state) {

		OrderState os = OrderState.valueOf(state);
		String path = "redirect:" + orderInfoUrl + "?id=" + id;
		synchronized (id.trim()) {
			OrdersBean order = ordersDao.load(id);
			ClientBean userInfo = userInfoService.getClientBean();
			// �ǿͻ�
			if (!userInfo.getID().equals(order.getClientID()))
				return null;

			OrderState orderState = order.getOrderStateE();
			if (os.equals(orderState)) {
				switch (order.getOrderStateE()) {

				case CD_CANCELED: // �ͻ�ȡ���������ͣ�ȷ���ûػ���˿�-----
					JoinOrderBean joinOrderBean = order.getJoinOrder();
					joinOrderBean.setFinishTime(new Date());
					joinOrderBean.setJoinState(JoinOrderState.CD_CANCEL);
					orderState = OrderState.PAY_CANCEL;
					payment.requestRefund(order.getID());
					break;

				case DD_CANCELED: // ˾��ȡ�����ͣ�ȷ���ûػ���������ӵ�-----
					JoinOrderBean joinOrder = order.getJoinOrder();
					joinOrder.setFinishTime(new Date());
					joinOrder.setJoinState(JoinOrderState.DD_CANCEL);
					orderState = OrderState.PAYED;
					break;

				case DD_CANCELING: // ˾������ȡ�����ͣ��ͻ�ȷ��
					orderState = OrderState.DD_CANCELED;
					break;
				default:
					return path;
				}
				order.setOrderState(orderState);
				order.setFinishTime(new Date());

				ordersDao.update(order);
			}
		}

		return path;
	}

	/**
	 * ���۶���
	 * 
	 * @param id
	 */
	@RequestMapping("/evaluate.xhtm")
	public ModelAndView evaluate(String id) {
		OrdersBean order = ordersDao.load(id);

		ModelAndView mv = new ModelAndView();
		mv.addObject(FinalInfo.ORDER, order);
		mv.setViewName("order_evaluate.jsp");

		return mv;
	}

	@Autowired
	ClientEvaluateDao evaluateDao;

	/**
	 * ���۶�������
	 * 
	 * @param id
	 */
	@RequestMapping("/evaluate.do")
	public String evaluateDeal(String id, String text, Double range) {
		synchronized (id) {
			OrdersBean order = ordersDao.load(id);
			boolean op = order.getOrderStateE().equals(OrderState.FINISH);
			if (op) {
				ClientEvaluateBean evaluate = new ClientEvaluateBean();
				evaluate.setText(text);
				evaluate.setRange(range);
				evaluate.setOrder(order);
				op = evaluateDao.insert(evaluate);
				if (op) {
					order.setOrderState(OrderState.FINISH_EVALUATE);
					op = ordersDao.update(order);
					if (!op)
						evaluateDao.delete(evaluate);
				}

			} else {
				return null;
			}
		}

		return "redirect:" + orderInfoUrl + "?id=" + id;
	}
}
