package cn.itcast.erp.biz.impl;

import java.util.List;

import cn.itcast.erp.dao.IBaseDao;

public class BaseBiz<T>{

	private IBaseDao baseDao;

	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao = baseDao;
	}

	
	public List<T> getList(T t1,T t2,Object param) {

		return baseDao.getList(t1,t2,param);
	}
	
	//分页
	public List<T> getListByPage(T t1, T t2, Object param , int firstResult,int maxResults) {
		return baseDao.getListByPage(t1, t2, param, firstResult, maxResults);
	}

	
	public Long getCount(T t1, T t2, Object param) {
		
		return baseDao.getCount(t1,t2,param);
	}
	
	public void add(T t){		
		baseDao.add(t);
	}

	
	public void delete(Long uuid) {
		baseDao.delete(uuid);	
	}

	
	public void update(T t) {
		baseDao.update(t);	
	}

	
	public T get(Long id) {
		return (T) baseDao.get(id);	
	}

}
