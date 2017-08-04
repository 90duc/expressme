package cn.expressme.client.dao;

import java.util.List;

import cn.expressme.client.bean.ClientEvaluateBean;

public interface ClientEvaluateDao {

	public abstract ClientEvaluateBean load(String id);
	
	public abstract double getAvgRange(String evaluated);

	public abstract boolean insert(ClientEvaluateBean evaluate);
	
	public abstract boolean update(ClientEvaluateBean evaluate);

	public abstract boolean delete(ClientEvaluateBean evaluate);

	public abstract List<ClientEvaluateBean> getByDriverID(String id, int size, int page);

}