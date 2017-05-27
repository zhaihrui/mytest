package cn.itcast.erp.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cn.itcast.erp.dao.IDepDao;
import cn.itcast.erp.entity.Dep;

public class DepDao extends BaseDao<Dep> implements IDepDao {


	public DetachedCriteria getDetachedCriterid(Dep t1, Dep t2, Object param) {
		DetachedCriteria dc = DetachedCriteria.forClass(Dep.class);
		if(t1!=null){
			if (t1.getName() != null && t1.getName().length() > 0) {
				dc.add(Restrictions.like("name", t1.getName(), MatchMode.ANYWHERE));// 进行模糊查询
			}
			if (t1.getTele() != null && t1.getTele().length() > 0) {
				dc.add(Restrictions.like("tele", t1.getTele(), MatchMode.ANYWHERE));// 进行模糊查询
			}
		}
		// 对查到的数据进行降序排列
		dc.addOrder(Order.desc("uuid"));
		return dc;

	}


}
