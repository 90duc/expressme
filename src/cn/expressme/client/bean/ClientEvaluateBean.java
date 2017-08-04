package cn.expressme.client.bean;

import java.io.Serializable;
import java.util.Date;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import cn.expressme.other.bean.OrderInfoBean;
import cn.expressme.other.bean.OrdersBean;
/**
 * ÆÀ·Ö
 * @author MK
 *
 */
@Entity
@Table(name="clientEvaluate")
public class ClientEvaluateBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9143474610602310117L;
	@Id  
	@GenericGenerator(name="generator",strategy="foreign",
	parameters=@Parameter(name="property",value="orderBill"))
	@GeneratedValue(generator="generator")
	private String ID;
		
	@Column(name="mark")
	private double range=10.0f;
	
	private String text;
	@Temporal(TemporalType.DATE)
	private Date time;
	
	@OneToOne(fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private OrdersBean order;
	
	
	
	public ClientEvaluateBean() {
		super();
		time=new Date();
	}


	public String getDriverID() {
		
		return order.getDriverID();
	}
	
	public double getRange() {
		return range;
	}
	public void setRange(Double range) {
		this.range = range==null?10:range;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getID() {
		return ID;
	}
	/*
	public void setID(String iD) {
		ID = iD;
	}*/
	public String getClientName() {
		return order.getClientBean().getUserName();
	}
	public OrdersBean getOrder() {
		return order;
	}

	

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}


	public String getGoodsName() {
		return order.getGoodsName();
	}


	public String getClientPhoto() {
		return order.getClientBean().getPhoto();
	}


	public void setOrder(OrdersBean order2) {
		this.order=order2;		
	}
}
