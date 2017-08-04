package cn.expressme.driver.dao;

import cn.expressme.driver.bean.DriverEvaluateBean;

public interface DriverEvaluateDao {

	public abstract DriverEvaluateBean load(String id);

	public abstract double getAvgRange(String evaluated);

	public abstract boolean insert(DriverEvaluateBean evaluate);
	
	public abstract boolean update(DriverEvaluateBean evaluate);

	public abstract boolean delete(DriverEvaluateBean evaluate);

}