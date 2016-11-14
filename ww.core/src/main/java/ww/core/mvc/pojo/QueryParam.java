package ww.core.mvc.pojo;

import java.util.ArrayList;
import java.util.List;

public class QueryParam {

	/**
	 * 查询条件
	 */
	private List<Object> conditionList;
	/**
	 * 排序
	 */
	private String sort;
	
	public QueryParam() {
		this.conditionList = new ArrayList<Object>();
	}
	
	public List<Object> getConditionList() {
		return conditionList;
	}
	public void setConditionList(List<Object> conditionList) {
		this.conditionList = conditionList;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
}
