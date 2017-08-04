package cn.expressme.client.dao;

import cn.expressme.client.bean.ClientBean;

public interface ClientDao {

	public abstract ClientBean getByPhone(String phone);

	public abstract ClientBean load(String id);

	public abstract ClientBean insert(ClientBean bean);

	public abstract boolean update(ClientBean clientBean);

	public abstract boolean delete(ClientBean userInfo);

}