package cn.expressme.driver.action;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.expressme.client.bean.ClientBean;
import cn.expressme.driver.bean.DriverBean;
import cn.expressme.other.bean.OrdersBean;
import cn.expressme.other.bean.OrderInfoable;
import cn.expressme.other.bean.OrdersBean;
import cn.expressme.other.bean.VerifyCodeBean;
import cn.expressme.other.dao.OrdersDao;
import cn.expressme.other.res.FinalInfo;
import cn.expressme.other.res.OrderState;
import cn.expressme.other.res.VerifyState;
import cn.expressme.other.service.OrderService;
import cn.expressme.other.service.PaymentService;
import cn.expressme.other.service.UserService;

@Controller
@RequestMapping("/driver")
public class DriverOrderAtion {

	// @Autowired
	// private OrderService orderService;
	@Autowired
	private OrdersDao ordersDao;
	@Autowired
	private UserService userInfoService;
	@Autowired
	private PaymentService payment;

	/**
	 * ���ӵ���������
	 * 
	 * @return
	 */
	@RequestMapping("/waitorder.xhtm")
	public ModelAndView waitReceiveOrder(String id) {

		DriverBean userInfo = userInfoService.getDriverBean();
		OrdersBean order = ordersDao.load(id);
		ModelAndView mv = null;
		if (userInfo.getVerifyStateE().equals(VerifyState.VERIFYED)
				&& Objects.equals(order.getOrderStateE(), OrderState.PAYED)) {
			mv = new ModelAndView();
			mv.addObject(FinalInfo.ORDERS, order);
			mv.setViewName("wait_order_info.jsp");
		}

		return mv;
	}

	/**
	 * ���ӵ������б�
	 * 
	 * @return
	 */
	@RequestMapping("/waitorders.xhtm")
	public ModelAndView waitReceiveOrders() {

		DriverBean userInfo = userInfoService.getDriverBean();
		ModelAndView mv = null;
		if (userInfo.getVerifyState().equals(VerifyState.VERIFYED)) {
			mv = new ModelAndView();
			List<OrdersBean> orders = ordersDao.getWithoutReceive();
			mv.addObject(FinalInfo.ORDERS, orders);
			mv.setViewName("waitorders.jsp");
		}

		return mv;
	}

	/**
	 * ɸѡδ�ӵ������б�
	 * 
	 */
	@RequestMapping("/fiterorder.do")
	public void selectReceiveOrders(String minAmount, String maxAmount,
			String startAddress, String endAddress, String MaxWeight,
			String SETime, String EETime) {

		DriverBean userInfo = userInfoService.getDriverBean();
		ModelAndView mv = null;
		if (userInfo.getVerifyState().equals(VerifyState.VERIFYED)) {
			// mv = new ModelAndView();
			// List<OrderBean> orders = orderDao.getOrdersWithoutReceive();
			// mv.addObject(FinalStringInfo.ORDERS, orders);
			// mv.setViewName("WExpressOrders.jsp");
		}

		// return mv;
	}

	/**
	 * ˾�����Ͷ���
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/expressorder.xhtm")
	public ModelAndView expressOrderInfo(String id) {

		ModelAndView mv = null;
		OrdersBean order = ordersDao.load(id);
		DriverBean user = userInfoService.getDriverBean();
		if (Objects.equals(user.getID(), order.getDriverID())
				&& Objects.equals(order.getOrderState(), OrderState.DELIVERY)) {

			mv = new ModelAndView();
			mv.addObject(FinalInfo.ORDER, order);
			mv.setViewName("express_order.jsp");
		}

		return mv;
	}

	/**
	 * ˾��ȷ�϶���
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/finishorder.do")
	public String finishOrder(String id, String verifyCode) {

		synchronized (id.intern()) {
			OrdersBean order = ordersDao.load(id);
			DriverBean user = userInfoService.getDriverBean();
			if (Objects.equals(user.getID(), order.getDriverID())
					&& Objects.equals(order.getOrderState(),
							OrderState.DELIVERY)
					&& order.getVerifyCode().equalsIgnoreCase(verifyCode)) { // ��֤�벻���ִ�Сд
				order.setOrderStateE(OrderState.FINISH);
				if (ordersDao.update(order))
					payment.payToDriver(order); // �����˾��
			}

		}
		return "redirect:orderinfo.xhtm";
	}

	/**
	 * ˾����������
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/orderinfo.xhtm")
	public ModelAndView showDriverOrderInfo(String id) {

		ModelAndView mv = null;
		if (userInfoService.getDriverBean().getVerifyState()
				.equals(VerifyState.VERIFYED)) {
			OrderInfoable order = ordersDao.load(id);
			mv = new ModelAndView();
			mv.addObject(FinalInfo.ORDER, order);

			mv.setViewName("order_info.jsp");
		}

		return mv;
	}

	/**
	 * ˾�������б�
	 * 
	 * @return
	 */
	@RequestMapping("/showorders.xhtm")
	public ModelAndView showDriverOrders() {

		DriverBean userInfo = userInfoService.getDriverBean();
		ModelAndView mv = null;
		if (userInfo.getVerifyState().equals(VerifyState.VERIFYED)) {
			mv = new ModelAndView();
			List<OrdersBean> orders = ordersDao.getByDriver(userInfo.getID());
			mv.addObject(FinalInfo.ORDERS, orders);
			mv.setViewName("show_orders.jsp");
		}

		return mv;
	}

	/**
	 * ȡ����������
	 * 
	 * @param id
	 */
	@RequestMapping("/cancelorder.do")
	public ModelAndView driverCancelOrder(String id) {

		String path = "FCancelOrder.jsp ";
		String text = "����ȡ��ʧ��";

		synchronized (id.intern()) {
			OrdersBean order = ordersDao.load(id);
			DriverBean userInfo = userInfoService.getDriverBean();
			// �ǿͻ�
			if (!userInfo.getID().equals(order.getDriverID()))
				return null;

			switch (order.getOrderStateE()) {

			case RECIEVE:   //�ӵ�ȡ��
				order.setOrderStateE(OrderState.PAY_CANCEL);
				order.setJoinOrderBean(null);
				break;

			case DELIVERY:
				order.setOrderStateE(OrderState.DD_CANCELING);
				break;

			default:
				break;

			}
			ordersDao.update(order);
		}

		

		ModelAndView mv = new ModelAndView();
		// mv.addObject("billNumber", billNumber);
		mv.addObject(FinalInfo.OPERATION_TEXT, text);
		mv.setViewName(path);

		return mv;
	}
}
