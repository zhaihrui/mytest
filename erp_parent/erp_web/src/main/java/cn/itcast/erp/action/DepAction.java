package cn.itcast.erp.action;

import cn.itcast.erp.biz.IDepBiz;
import cn.itcast.erp.entity.Dep;

public class DepAction extends BaseAction<Dep>{
	private IDepBiz depBiz;

	public IDepBiz getDepBiz() {
		return depBiz;
	}

	public void setDepBiz(IDepBiz depBiz) {
		this.depBiz = depBiz;
		setBaseBiz(depBiz);
	}
	
}
