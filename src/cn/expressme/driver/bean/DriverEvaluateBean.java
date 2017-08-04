package cn.expressme.driver.bean;

import java.io.Serializable;

import javassist.tools.framedump;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import cn.expressme.other.bean.OrderInfoable;
import cn.expressme.other.bean.OrdersBean;
/**
 * ÆÀ·Ö
 * @author MK
 *
 */
@Entity
@Table(name="driverEvaluate")
public class DriverEvaluateBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9143474610602310117L;
	@Id  
	@GenericGenerator(name="generator",strategy="foreign",
	parameters=@Parameter(name="property",value="order"))
	@GeneratedValue(generator="generator")
	private String ID;
	
	@Column(name="mark")
	private float range=10.0f;
	
	private String text;
	
		
	@OneToOne(cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private OrdersBean order;
	
	public String getOrderID() {
		return ID;
	}

	public String getClientID() {
		return order.getClientID();//clientID==null?order.getClientID():clientID;
	}
	
	public String getDriverID() {
		
		return order.getDriverID();//driverID==null?order.getDriverID():driverID;
	}
	
	public float getRange() {
		return range;
	}
	public void setRange(float range) {
		this.range = range;
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

	public OrdersBean getOrder() {
		return order;
	}

	public void setOrder(OrdersBean order) {
		this.order = order;
	}
}
