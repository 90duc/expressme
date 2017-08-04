package cn.expressme.driver.dao;

import cn.expressme.driver.bean.DriverBean;

public interface DriverDao {

	public abstract DriverBean getByPhone(String phone);

	public abstract DriverBean load(String id);

	public abstract DriverBean insert(String userID);

	public abstract boolean update(DriverBean driverBean);

	public abstract boolean delete(DriverBean driverBean);

}