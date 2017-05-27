package cn.itcast.erp.biz;

import java.util.List;

public interface IBaseBiz<T> {
	public List<T> getList(T t1,T t2,Object param);
	public List<T> getListByPage(T t1, T t2, Object param , int firstResult,int maxResults);
	public Long getCount(T t1, T t2, Object param);
	public void add(T t);
	public void delete(Long uuid);
	public void update(T t);
	public T get(Long id);
}
