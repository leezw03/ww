package ww.db.mybatis.pojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 查询参数类
 * 定义查询的条件和参数
 * @author leezw
 *
 */
public class QueryParam {

	/**
	 * 查询条件
	 * 通过where后面追加
	 */
	private List<Object> conditionList;
	/**
	 * 变量参数
	 * 通过替换sql中的关键词
	 */
	private Map<String, String> varMap;
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
	public Map<String, String> getVarMap() {
		return varMap;
	}
	public void setVarMap(Map<String, String> varMap) {
		this.varMap = varMap;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}

}
