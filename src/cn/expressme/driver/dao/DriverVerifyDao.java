package cn.expressme.driver.dao;

import cn.expressme.driver.bean.DriverVerifyBean;

public interface DriverVerifyDao {

	public abstract DriverVerifyBean load(String id);

	public abstract boolean insert(DriverVerifyBean verify);

	public abstract boolean update(DriverVerifyBean verify);

	public abstract boolean delete(DriverVerifyBean verify);

}