package cn.expressme.other.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "payment")
public class PaymentBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5839675157609038424L;
	
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid2")
	@GeneratedValue(generator = "generator")
	private String ID;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orderID")
	private OrdersBean ordersBean;
	
	private String payType;
	
	private String payID;
	
	private Date payTime=new Date();
	
	private String state;

	public String getID() {
		return ID;
	}


	public OrdersBean getOrdersBean() {
		return ordersBean;
	}

	public void setOrdersBean(OrdersBean ordersBean) {
		this.ordersBean = ordersBean;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getPayID() {
		return payID;
	}

	public void setPayID(String payID) {
		this.payID = payID;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
