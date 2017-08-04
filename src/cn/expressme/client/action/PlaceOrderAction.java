package cn.expressme.client.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.expressme.client.bean.ClientBean;
import cn.expressme.other.bean.OrderInfoBean;
import cn.expressme.other.bean.OrderInfoable;
import cn.expressme.other.bean.OrdersBean;
import cn.expressme.other.dao.OrdersDao;
import cn.expressme.other.res.DeliveryType;
import cn.expressme.other.res.FinalInfo;
import cn.expressme.other.res.Goods;
import cn.expressme.other.res.PersonInfo;
import cn.expressme.other.res.Point;
import cn.expressme.other.service.UserService;
import cn.expressme.other.util.Util;

@Controller
@RequestMapping("/client")
public class PlaceOrderAction {

	@Autowired
	private UserService userService;
	@Autowired
	private HttpSession session;
	@Autowired
	private OrdersDao ordersDao;

	/**
	 * 保存起始和目的地址
	 * 
	 * @param order
	 * @return
	 */
	// @ResponseBody
	@RequestMapping(value = "/saveaddr.do", method = RequestMethod.POST)
	public String saveAddress(String startAddress, String endAddress,
			Double startPX, Double startPY, Double endPX, Double endPY) {

		OrderInfoBean order=new OrderInfoBean();
		if (startPX == null | startPY == null) {
			order.setStartPoint(new Point(120, 30));
			order.setEndPoint(new Point(120, 30.05));

		} else {
			order.setStartPoint(new Point(startPX, startPY));
			order.setEndPoint(new Point(endPX, endPY));
		}
		order.setStartAddress(startAddress);
		order.setEndAddress(endAddress);
		session.setAttribute(FinalInfo.NEW_ORDER, order);

		return "redirect:" + placeOrderUrl;
		// double
		// distance=Util.getMapDistance(order.getStartPoint(),order.getEndPoint());
		// order.setAmount(UserService.getPrice(distance));

		// String
		// data="{\"success\":true,\""+FinalInfo.PRICE+"\":"+order.getAmount()
		// +",\""+FinalInfo.DISTANCE+"\":"+String.format("%.2f", distance)
		// +",\""+FinalInfo.TIME+"\":"+UserService.getTime(distance)+"}";
		// return data;
	}

	private final String placeOrderUrl = "placeorder.xhtm";
	private final String placeOrderPath = "place_order.jsp";

	/**
	 * 开始下单
	 * 
	 * @return
	 */
	@RequestMapping(value = placeOrderUrl)
	public ModelAndView placeOrder() {

		OrderInfoBean order = (OrderInfoBean) session.getAttribute(FinalInfo.NEW_ORDER);
		double distance = Util.getMapDistance(order.getStartPoint(),
				order.getEndPoint());
		order.setAmount(UserService.getPrice(distance));
		ClientBean user = userService.getClientBean();
		ModelAndView mv = new ModelAndView();
		mv.addObject(FinalInfo.ORDER, order);		
		mv.addObject(FinalInfo.USER_INFO, user);
		mv.addObject(FinalInfo.PRICE, order.getAmount());
		mv.addObject(FinalInfo.DISTANCE, String.format("%.2f", distance));
		mv.addObject(FinalInfo.TIME, UserService.getTime(distance));
		mv.setViewName(placeOrderPath);
		return mv;
	}

	/**
	 * 商品信息
	 * 
	 * @param order
	 * @param goodsPhoto
	 * @return
	 */
	@RequestMapping(value = "/goods.xhtm")
	public ModelAndView getGoods() {

		OrderInfoable order = (OrderInfoable) session.getAttribute(FinalInfo.NEW_ORDER);
		ModelAndView mv = new ModelAndView();
		mv.addObject(FinalInfo.ORDER, order);
		mv.setViewName("order_goods_info.jsp");
		return mv;
	}

	/**
	 * 保存商品信息
	 * 
	 * @param order
	 * @param goodsPhoto
	 * @return
	 */
	@RequestMapping(value = "/savegoods.do", method = RequestMethod.POST)
	public String saveGoods(
			@RequestParam(value = "goodsPhotoFile", required = false) CommonsMultipartFile goodsPhoto,
			@ModelAttribute Goods goods, String deliverType) {

		OrderInfoBean order = (OrderInfoBean) session.getAttribute(FinalInfo.NEW_ORDER);
		if (!order.hasGoods())
			order.setGoods(goods);
		else {
			order.getGoods().update(goods);
		}
		if(Util.isNullOrEmpty(deliverType)){
			try {
				order.setDeliveryType(DeliveryType.valueOf(deliverType));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (!Util.isNullOrEmpty(goodsPhoto)) {
			// 源图片类型
			String oldName = goodsPhoto.getOriginalFilename();
			int index = oldName.lastIndexOf(".");
			index = index > -1 ? index : 0;
			String type = oldName.substring(index);
			// 生成相对webapps的相对路径
			String idname;
			if(order.hasPhoto())
				idname=order.getGoodsName();
			else {
				idname= Util.UUID() + type;
			}
			String name = FinalInfo.GOODS_PHOTO_PATH + idname; // 生成随机图片名
			try {
				File file = new File(UserService.getRootURL(), name);
				file.getParentFile().mkdirs();
				goodsPhoto.transferTo(file); // 保存图片
				goods.setGoodsPhoto(name); // 设置图片路径
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		// System.out.println("12345");
		return "redirect:" + "placeorder.xhtm";
	}

	/**
	 * 保存地址和期待时间
	 * 
	 * @param order
	 * @param goodsPhoto
	 * @return
	 */
	@RequestMapping(value = "/placeorder.do", method = RequestMethod.POST)
	public String saveTime(String SETime, String EETime) {

		OrderInfoBean order = (OrderInfoBean) session.getAttribute(FinalInfo.NEW_ORDER);
		if (!Util.isNullOrEmpty(SETime)) {
			order.setSETime(Util.formateDate(SETime));
		}
		if (!Util.isNullOrEmpty(EETime)) {
			order.setEETime(Util.formateDate(EETime));
		}

		return "redirect:" + "finishorder.xhtm";
	}

	private final String finishOrderPath = "order_taking_info.jsp";

	/**
	 * 发货人和收货人信息 和小费
	 * 
	 * @param order
	 * @param goodsPhoto
	 * @return
	 */
	@RequestMapping(value = "/finishorder.xhtm")
	public ModelAndView placeOrder2() {

		OrderInfoBean order = (OrderInfoBean) session.getAttribute(FinalInfo.NEW_ORDER);
		double distance = Util.getMapDistance(order.getStartPoint(),
				order.getEndPoint());
		order.setAmount(UserService.getPrice(distance));
		ModelAndView mv = new ModelAndView();
		mv.addObject(FinalInfo.PRICE, order.getAmount());
		mv.addObject(FinalInfo.DISTANCE, String.format("%.2f", distance));
		mv.addObject(FinalInfo.TIME, UserService.getTime(distance));
		mv.setViewName(finishOrderPath);
		return mv;
	}

	private static final String successPlaceOrderPage = "orderinfo.xhtm";
	private static final String failPlaceOrderPage = "";

	/**
	 * 完成下单
	 * 
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/finishorder.do", method = RequestMethod.POST)
	public ModelAndView placeOrder3(PersonInfo personInfo, Double fee,
			String remark) {

		OrderInfoBean order = (OrderInfoBean) session.getAttribute(FinalInfo.NEW_ORDER);
		order.setPersonInfo(personInfo);
		order.setFee(fee);
		order.setRemark(remark);
		
		OrdersBean ordersBean=new OrdersBean(userService.getClientBean(),order);

		String path = failPlaceOrderPage;
		if (ordersDao.insert(ordersBean)) {
			path = successPlaceOrderPage;
			session.removeAttribute(FinalInfo.NEW_ORDER);
		}
		ModelAndView mView = new ModelAndView();
		mView.addObject("id", order.getID());
		mView.setViewName("redirect:" + path);
		return mView;
	}

}
