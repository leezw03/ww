package ww.core.mvc.pojo;

import java.util.ArrayList;
import java.util.List;

public class QueryParam {

	/**
	 * 查询条件
	 */
	private List<IQueryCondition> conditionList;
	/**
	 * 排序
	 */
	private String sort;
	
	public QueryParam() {
		this.conditionList = new ArrayList<IQueryCondition>();
	}
	
	public List<IQueryCondition> getConditionList() {
		return conditionList;
	}
	public void setConditionList(List<IQueryCondition> conditionList) {
		this.conditionList = conditionList;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
}
