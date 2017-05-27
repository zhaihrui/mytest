package cn.itcast.erp.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;

import cn.itcast.erp.biz.IBaseBiz;

public class BaseAction<T> {
	private IBaseBiz baseBiz;
	private T t1;
	private T t2;
	private T t;
	private Object param;
	private int page;//页数
	private int rows;//每页最大条数
	private Long id;
	
	
	public IBaseBiz getBaseBiz() {
		return baseBiz;
	}

	public void setBaseBiz(IBaseBiz baseBiz) {
		this.baseBiz = baseBiz;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public T getT1() {
		return t1;
	}

	public void setT1(T t1) {
		this.t1 = t1;
	}

	public T getT2() {
		return t2;
	}

	public void setT2(T t2) {
		this.t2 = t2;
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	public Object getParam() {
		return param;
	}

	public void setParam(Object param) {
		this.param = param;
	}



	public void list(){
		List<T> list = baseBiz.getList(t1,t2,param);
		String jsonString = JSON.toJSONString(list);
		writeJson(jsonString);
	}
	
	public void writeJson(String jsonString){
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.setCharacterEncoding("utf-8");  // 转码
			response.getWriter().write(jsonString);  // 写出json数据
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//分页
	public void getListByPage(){
		int firstResult = (page-1)*rows;//求出第一条数据的起始位置
		List<T> list = baseBiz.getListByPage(t1, t2, param, firstResult, rows);
		//分装分页查询的pagetion数据的格式
		Map map = new HashMap();
		map.put("total", baseBiz.getCount(t1,t2,param));
		map.put("rows", list);
		String jsonString = JSON.toJSONString(map);
		writeJson(jsonString);
	}
	
	//增加
	public void add(){
		try {
			baseBiz.add(t);
			writeJson(ajaxReturn(true, "添加成功"));
		} catch (Exception e) {
			e.printStackTrace();
			writeJson(ajaxReturn(false, "失败成功"));
		}
		
	}
	
	//删除的方法
	public void delete(){
		try {
			baseBiz.delete(id);
			writeJson(ajaxReturn(true, "删除成功"));
		} catch (Exception e) {
			writeJson(ajaxReturn(false, "删除失败"));
			e.printStackTrace();
		}
	}
	
	//修改数据
	public void update(){
		try {
			baseBiz.update(t);
			writeJson(ajaxReturn(true, "修改成功"));
		} catch (Exception e) {
			writeJson(ajaxReturn(false, "失败成功"));
			e.printStackTrace();
		}
	}

	//根据id获取数据
	public void get(){
		T t = (T) baseBiz.get(id);
		String jsonString = JSON.toJSONString(t);
		writeJson(mapJson(jsonString, "t"));
	}

	//为了回显数据
	public String mapJson(String jsonString,String prefix){
		Map newMap = new HashMap();
		Map<String ,Object> map = JSON.parseObject(jsonString);
		for (String key : map.keySet()) {
			newMap.put(prefix+"."+key, map.get(key));
		}
		return JSON.toJSONString(newMap);
		
		
	}
	
	//封装一个map集合给前端用
	public String ajaxReturn(boolean success, String message ){
		Map map = new HashMap();
		map.put("success", success);
		map.put("message", message);
		String jsonString = JSON.toJSONString(map);
		return jsonString;	
	}
}
