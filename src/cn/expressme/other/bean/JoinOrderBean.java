package cn.expressme.other.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import cn.expressme.driver.bean.DriverBean;
import cn.expressme.other.res.JoinOrderState;

@Entity
@Table(name = "joinorder")
public class JoinOrderBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5045746194118747814L;

	/**
	 * 
	 */
	
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid2")
	@GeneratedValue(generator = "generator")
	private String ID;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orderID")
	private OrdersBean order;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "driverID")
	private DriverBean driverBean;

	@Temporal(TemporalType.TIMESTAMP)
	private Date receiveTime;
	@Temporal(TemporalType.TIMESTAMP)
	private Date deliverTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date finishTime;

	@Enumerated(EnumType.STRING)
	private JoinOrderState  joinState =  JoinOrderState.RECIEVE;

	public OrdersBean getOrder() {
		return order;
	}

	public void setOrder(OrdersBean order) {
		this.order = order;
	}

	public DriverBean getDriver() {
		return driverBean;
	}

	public void setDriver(DriverBean driver) {
		this.driverBean = driver;
	}

	public Date getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	public Date getDeliverTime() {
		return deliverTime;
	}

	public void setDeliverTime(Date deliverTime) {
		this.deliverTime = deliverTime;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public JoinOrderState getJoinState() {
		return joinState;
	}

	public void setJoinState(JoinOrderState joinState) {
		this.joinState = joinState;
	}

	public String getID() {
		return ID;
	}

	
}
