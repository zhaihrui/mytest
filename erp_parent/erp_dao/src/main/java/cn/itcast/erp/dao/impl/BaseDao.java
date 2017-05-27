package cn.itcast.erp.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public class BaseDao<T> extends HibernateDaoSupport{
	private Class<T> entityClass;
	
	public BaseDao(){
		Type type = getClass().getGenericSuperclass();
		ParameterizedType ptype = (ParameterizedType)type;
		Type[] types = ptype.getActualTypeArguments();
		entityClass=(Class<T>) types[0];
	}
	//条件查询
	
	public List<T> getList(T t1, T t2, Object param) {
		DetachedCriteria dc = getDetachedCriterid(t1, t2, param);
		return (List<T>) this.getHibernateTemplate().findByCriteria(dc);
	}

	//分页查询
	public List<T> getListByPage(T t1, T t2, Object param , int firstResult,int maxResults) {
		DetachedCriteria dc = getDetachedCriterid(t1, t2,param);
		return (List<T>) this.getHibernateTemplate().findByCriteria(dc, firstResult, maxResults);
	} 
	
	//查询总记录条数
	
	public Long getCount(T t1, T t2, Object param) {
		DetachedCriteria dc = getDetachedCriterid(t1, t2,param);
		dc.setProjection(Projections.rowCount());//投影查询
		List list = getHibernateTemplate().findByCriteria(dc);
		return (Long)list.get(0);
	}
	
	public DetachedCriteria getDetachedCriterid(T t1, T t2, Object param) {
	
		return null;

	}

	
	public void add(T t) {
		this.getHibernateTemplate().save(t);
	}

	//根据id删除部门
	public void delete(Long uuid){
		this.getHibernateTemplate().delete(get(uuid));
	}
	
	//根据id获取一条记录
	public T get(Long uuid){
		return this.getHibernateTemplate().get(entityClass, uuid);
		
	}

	
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}
	

}
