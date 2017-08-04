package cn.expressme.other.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import cn.expressme.client.bean.ClientBean;
import cn.expressme.driver.bean.DriverBean;
import cn.expressme.other.res.Goods;
import cn.expressme.other.res.OrderState;
import cn.expressme.other.res.PersonInfo;
import cn.expressme.other.res.Point;
import cn.expressme.other.util.Util;

@Entity
@Table(name = "orderBill")
public class OrdersBean implements Serializable , OrderInfoable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4321397818008142827L;

	@Id
	@GenericGenerator(name = "generator", strategy = "uuid2")
	@GeneratedValue(generator = "generator")
	private String ID;

	
	private String verifyCode;

	@Enumerated(EnumType.STRING)
	private OrderState orderState = OrderState.NONE;

	@JoinColumn(name = "clientID")
	@ManyToOne(fetch = FetchType.LAZY)
	private ClientBean clientBean;

	@OneToOne(fetch = FetchType.LAZY,cascade={CascadeType.ALL})
	@JoinColumn(name = "payID")
	private PaymentBean paymentBean;
	
	
	@OneToOne(fetch = FetchType.LAZY,cascade={CascadeType.ALL})
	@JoinColumn(name = "joinOrderID")
	private JoinOrderBean joinOrderBean;

	@Temporal(TemporalType.TIMESTAMP)
	private Date placeTime;
	
	
	@OneToOne(fetch=FetchType.LAZY,cascade={CascadeType.ALL})
	@PrimaryKeyJoinColumn
	private OrderInfoBean orderInfoBean;

	@Temporal(TemporalType.TIMESTAMP)
	private Date finishTime;
	
	public OrdersBean() {
		
	}

	
	public OrdersBean(ClientBean clientBean, OrderInfoBean orderInfoBean) {
		super();
		placeTime=new Date();
		this.clientBean = clientBean;
		this.orderInfoBean = orderInfoBean;
		orderInfoBean.setOrdersBean(this);
	}


	public String getBillNumber() {
		return ID;
	}

	
	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getOrderState() {
		return orderState.getTitle();
	}

	/**
	 * 订单状态类型
	 * 
	 * @return
	 */
	public String getOSType() {

		return orderState.getType();
	}

	/**
	 * 订单状态
	 * 
	 * @return
	 */
	public String getOSName() {
		return orderState.getName();
	}

	public void setOrderStateByString(String os) {
		this.orderState = OrderState.valueOf(OrderState.class, os);
	}

	public OrderState getOrderStateE() {
		return orderState;
	}

	public synchronized void setOrderStateE(OrderState os) {
		this.orderState = os;
	}

	public String getDriverName() {
		return joinOrderBean==null?null:joinOrderBean.getDriver().getUserName();
	}

	public String getID() {
		return ID;
	}

	public void setID(String id) {
		this.ID = id;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof OrdersBean))
			return false;
		OrdersBean order = (OrdersBean) obj;
	
		return Objects.equals(this.ID, order.ID);
	}


	public void setClientBean(ClientBean clientBean) {
		this.clientBean = clientBean;
	}

	public String getPlaceTime() {
		return Util.formateDate(placeTime);
	}

	public void setPlaceTime(Date placeTime) {
		this.placeTime = placeTime;
	}

	public ClientBean getClientBean() {
		return clientBean;
	}

	public DriverBean getDriverBean() {
		return joinOrderBean.getDriver();
	}
	public void setOrderState(OrderState orderState) {
		this.orderState = orderState;
	}

	

	public String getClientID() {

		return clientBean == null ? null : clientBean.getID();
	}

	public String getDriverID() {
		return joinOrderBean == null ? null : joinOrderBean.getDriver().getID();
	}

	
	public Date getPlaceTimeD() {

		return placeTime;
	}

	
	public String getDeliverTime() {
		return Util.formateDate(joinOrderBean.getDeliverTime());
	}
	public boolean hasEvaluated() {
		return orderState.equals(OrderState.FINISH_EVALUATE);
	}

	

	@Override
	public int hashCode() {
		if(ID==null)
			return this.hashCode();
		return ID.intern().hashCode();
	}

	public JoinOrderBean getJoinOrder() {
		return joinOrderBean;
	}


	@Override
	public String getStartAddress() {
		
		return orderInfoBean.getStartAddress();
	}


	@Override
	public String getEndAddress() {
		
		return orderInfoBean.getEndAddress();
	}


	@Override
	public String getShipper() {
		
		return orderInfoBean.getShipper();
	}


	@Override
	public String getShipperPhone() {
		
		return orderInfoBean.getShipperPhone();
	}


	@Override
	public String getReceiver() {
		
		return orderInfoBean.getReceiver();
	}


	@Override
	public String getReceiverPhone() {
		
		return orderInfoBean.getReceiverPhone();
	}


	@Override
	public String getGoodsPhoto() {
		
		return orderInfoBean.getGoodsPhoto();
	}


	@Override
	public String getGoodsName() {
		
		return orderInfoBean.getGoodsName();
	}


	@Override
	public Double getGoodsValue() {
		
		return orderInfoBean.getGoodsValue();
	}


	@Override
	public Double getGoodsWeight() {
		
		return orderInfoBean.getGoodsWeight();
	}


	@Override
	public Double getGoodsLength() {
		
		return orderInfoBean.getGoodsLength();
	}


	@Override
	public Double getGoodsWidth() {
		
		return orderInfoBean.getGoodsWidth();
	}


	@Override
	public Double getGoodsHeight() {
		
		return orderInfoBean.getGoodsHeight();
	}


	@Override
	public Double getAmount() {
		
		return orderInfoBean.getAmount();
	}


	@Override
	public String getSETime() {
		
		return orderInfoBean.getSETime();
	}


	@Override
	public String getEETime() {
		
		return orderInfoBean.getEETime();
	}


	@Override
	public Point getStartPoint() {
		
		return orderInfoBean.getStartPoint();
	}


	@Override
	public Point getEndPoint() {
		
		return orderInfoBean.getEndPoint();
	}


	@Override
	public double getFee() {
		
		return orderInfoBean.getFee();
	}


	@Override
	public double getTotalMoney() {
		
		return orderInfoBean.getTotalMoney();
	}


	@Override
	public String getRemark() {
		
		return orderInfoBean.getRemark();
	}


	@Override
	public PersonInfo getPersonInfo() {
		
		return orderInfoBean.getPersonInfo();
	}


	@Override
	public Goods getGoods() {
		
		return orderInfoBean.getGoods();
	}


	@Override
	public boolean hasGoodsWeight() {
		
		return orderInfoBean.hasGoodsWeight();
	}


	@Override
	public boolean hasGoodsVolume() {
		
		return orderInfoBean.hasGoodsVolume();
	}


	@Override
	public boolean hasGoodsValue() {
		
		return orderInfoBean.hasGoodsValue();
	}


	@Override
	public boolean hasExpectTime() {
		
		return orderInfoBean.hasExpectTime();
	}


	@Override
	public boolean hasRemark() {
		
		return orderInfoBean.hasRemark();
	}


	@Override
	public boolean hasGoods() {
		
		return orderInfoBean.hasGoods();
	}


	@Override
	public boolean hasPhoto() {
		
		return orderInfoBean.hasPhoto();
	}


	@Override
	public String getDeliveryType() {
		
		return orderInfoBean.getDeliveryType();
	}


	public String getPayTime() {
		return Util.formateDate(paymentBean.getPayTime());
	}
	public OrderInfoBean getOrderInfoBean() {
		return orderInfoBean;
	}


	public void setOrderInfoBean(OrderInfoBean orderInfoBean) {
		this.orderInfoBean = orderInfoBean;
	}


	public JoinOrderBean getJoinOrderBean() {
		return joinOrderBean;
	}


	public void setJoinOrderBean(JoinOrderBean joinOrderBean) {
		this.joinOrderBean = joinOrderBean;
	}


	public String getFinishTime() {
		return Util.formateDate(finishTime);
	}


	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}


	public PaymentBean getPaymentBean() {
		return paymentBean;
	}


	public void setPaymentBean(PaymentBean paymentBean) {
		this.paymentBean = paymentBean;
	}


	public Date getFinishTimeD() {
		
		return finishTime;
	}



}
