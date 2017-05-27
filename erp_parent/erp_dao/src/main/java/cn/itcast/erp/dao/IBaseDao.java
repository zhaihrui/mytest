package cn.itcast.erp.dao;

import java.util.List;

public interface IBaseDao<T> {
	public List<T> getList(T t1,T t2,Object param);

	//分页
	public List<T> getListByPage(T t1, T t2, Object paramint , int firstResult,int maxResults) ;

	//获得总记录条数
	public Long getCount(T t1, T t2, Object param);

	//添加的方法
	public void add (T t);
	
	//删除的方法
	public void delete(Long uuid);
	
	//修改的功能
	public void update(T t);
	
	//根据ID获取数据
	public T get(Long uuid);
	
}
