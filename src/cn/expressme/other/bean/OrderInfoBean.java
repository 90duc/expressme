package cn.expressme.other.bean;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.PrimaryKeyJoinColumns;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import cn.expressme.client.bean.ClientBean;
import cn.expressme.driver.bean.DriverBean;
import cn.expressme.other.res.DeliveryType;
import cn.expressme.other.res.FinalInfo;
import cn.expressme.other.res.Goods;
import cn.expressme.other.res.OrderState;
import cn.expressme.other.res.PersonInfo;
import cn.expressme.other.res.Point;
import cn.expressme.other.util.Util;

@Entity
@Table(name = "orderinfo")
public class OrderInfoBean implements Serializable, OrderInfoable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4321397818008142827L;

	@Id  
	@GenericGenerator(name="generator",strategy="foreign",
	parameters=@Parameter(name="property",value="ordersBean"))
	@GeneratedValue(generator="generator")
	private String ID;

	private String startAddress;
	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "x", column = @Column(name = "startPX")),
			@AttributeOverride(name = "y", column = @Column(name = "startPY")) })
	private Point startPoint;

	private String endAddress;
	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "x", column = @Column(name = "endPX")),
			@AttributeOverride(name = "y", column = @Column(name = "endPY")) })
	private Point endPoint;
		
	@Embedded
	private PersonInfo personInfo;


	@Embedded
	private Goods goods;
	
	@Enumerated(EnumType.STRING)
    private DeliveryType deliveryType;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date SETime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date EETime;

	private Double fee;

	private String remark;
	private Double amount = 0.0;

	@OneToOne(fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private OrdersBean ordersBean;

	@Override
	public String getBillNumber() {
		return ID;
	}

	@Override
	public String getStartAddress() {
		return startAddress;
	}

	public void setStartAddress(String startAddress) {
		this.startAddress = startAddress;
	}

	@Override
	public String getEndAddress() {
		return endAddress;
	}

	public void setEndAddress(String endAddress) {
		this.endAddress = endAddress;
	}

	@Override
	public String getShipper() {
		return personInfo.getShipper();
	}

	@Override
	public String getShipperPhone() {
		return personInfo.getShipperPhone();
	}

	@Override
	public String getReceiver() {
		return personInfo.getReceiver();
	}

	@Override
	public String getReceiverPhone() {
		return personInfo.getReceiverPhone();
	}

	public String getID() {
		return ID;
	}

	

	@Override
	public String getGoodsPhoto() {
		if(goods==null)
			return FinalInfo.DEFAULT_GOODS_PHOTO;
		return goods.getGoodsPhoto();
	}
	@Override
	public String getGoodsName() {
		if(goods==null)
			return null;
		return goods.getGoodsName();
	}

	@Override
	public Double getGoodsValue() {
		if(goods==null)
			return null;
		return goods.getGoodsValue();
	}

	@Override
	public Double getGoodsWeight() {
		if(goods==null)
			return null;
		return goods.getGoodsWeight();
	}

	@Override
	public Double getGoodsLength() {
		if(goods==null)
			return null;
		return goods.getGoodsLength();
	}

	@Override
	public Double getGoodsWidth() {
		if(goods==null)
			return null;
		return goods.getGoodsWidth();
	}

	@Override
	public Double getGoodsHeight() {
		if(goods==null)
			return null;
		return goods.getGoodsHeight();
	}

	@Override
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof OrderInfoBean))
			return false;
		OrderInfoBean order = (OrderInfoBean) obj;
	
		return Objects.equals(this.ID, order.ID);
	}


	public void setSETime(Date sETime) {
		SETime = sETime;
	}

	public void setEETime(Date eETime) {
		EETime = eETime;
	}

	

	@Override
	public String getSETime() {
		return Util.formateDate(SETime);
	}

	@Override
	public String getEETime() {
		return Util.formateDate(EETime);
	}

	

	@Override
	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	@Override
	public Point getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	@Override
	public double getFee() {
		return fee==null ? 0:fee;
	}

	@Override
	public double getTotalMoney() {
		return  amount==null?0:amount;
	}

	public void setFee(Double fee) {
		this.fee =  fee;
	}

	@Override
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public PersonInfo getPersonInfo() {
		return personInfo;
	}

	public void setPersonInfo(PersonInfo personInfo) {
		this.personInfo = personInfo;
	}

	@Override
	public Goods getGoods() {
		return goods;
	}


	
	public Date getSETimeD() {
		return SETime;
	}

	
	public Date getEETimeD() {
		return EETime;
	}


	@Override
	public boolean hasGoodsWeight() {
		return goods!=null&&this.goods.getGoodsWeight()!=null;
	}

	@Override
	public boolean hasGoodsVolume() {
		return goods!=null&&this.goods.getGoodsLength() !=null
				&& this.goods.getGoodsWidth() != null
				&& this.goods.getGoodsHeight() != null;
	}

	@Override
	public boolean hasGoodsValue() {
		return goods!=null&&this.goods.getGoodsValue() != null;
	}

	@Override
	public boolean hasExpectTime() {
		return this.SETime != null || this.EETime != null;
	}

	@Override
	public boolean hasRemark() {
		return !Util.isNullOrEmpty(this.remark);
	}
	
	@Override
	public boolean hasGoods() {
		return goods!=null;
	}
	@Override
	public boolean hasPhoto() {
		if(goods!=null)
			return !Util.isNullOrEmpty(goods.getGoodsPhoto());
		return false;
	}

	@Override
	public String getDeliveryType() {
		return deliveryType==null?null:deliveryType.getValue();
	}


	public DeliveryType getDeliveryTypeE() {
		return deliveryType;
	}
	
	public void setDeliveryType(DeliveryType deliveryType) {
		this.deliveryType = deliveryType;
	}

	@Override
	public int hashCode() {
		if(ID==null)
			return this.hashCode();
		return ID.intern().hashCode();
	}

	public OrdersBean getOrdersBean() {
		return ordersBean;
	}

	public void setOrdersBean(OrdersBean ordersBean) {
		this.ordersBean = ordersBean;
	}


}
