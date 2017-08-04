package cn.expressme.other.bean;

import java.util.Date;

import cn.expressme.other.res.Goods;
import cn.expressme.other.res.PersonInfo;
import cn.expressme.other.res.Point;

public interface OrderInfoable {

	public abstract String getBillNumber();

	public abstract String getStartAddress();

	public abstract String getEndAddress();

	public abstract String getShipper();

	public abstract String getShipperPhone();

	public abstract String getReceiver();

	public abstract String getReceiverPhone();

	public abstract String getGoodsPhoto();

	public abstract String getGoodsName();

	public abstract Double getGoodsValue();

	public abstract Double getGoodsWeight();

	public abstract Double getGoodsLength();

	public abstract Double getGoodsWidth();

	public abstract Double getGoodsHeight();

	public abstract Double getAmount();

	public abstract String getSETime();

	public abstract String getEETime();

	public abstract Point getStartPoint();

	public abstract Point getEndPoint();

	public abstract double getFee();

	public abstract double getTotalMoney();

	public abstract String getRemark();

	public abstract PersonInfo getPersonInfo();

	public abstract Goods getGoods();

	public abstract boolean hasGoodsWeight();

	public abstract boolean hasGoodsVolume();

	public abstract boolean hasGoodsValue();

	public abstract boolean hasExpectTime();

	public abstract boolean hasRemark();

	public abstract boolean hasGoods();

	public abstract boolean hasPhoto();

	public abstract String getDeliveryType();


}