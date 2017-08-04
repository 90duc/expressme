package cn.expressme.client.dao;

import cn.expressme.client.bean.ClientVerifyBean;

public interface ClientVerifyDao {

	public abstract ClientVerifyBean load(String id);

	public abstract boolean insert(ClientVerifyBean verify);

	public abstract boolean update(ClientVerifyBean verify);

	public abstract boolean delete(ClientVerifyBean verify);

}