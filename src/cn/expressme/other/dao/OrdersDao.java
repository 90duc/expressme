package cn.expressme.other.dao;

import java.util.List;

import cn.expressme.other.bean.OrderInfoBean;
import cn.expressme.other.bean.OrdersBean;

public interface OrdersDao {

	public abstract OrdersBean load(String id);

	public abstract List<OrdersBean> getByDriver(String id);

	public abstract List<OrdersBean> getByClient(String id, int size, int page);

	public abstract boolean insert(OrdersBean order);

	public abstract boolean update(OrdersBean order);

	public abstract boolean delete(OrdersBean order);

	public abstract List<OrdersBean> getWithoutReceive();

}