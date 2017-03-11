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
public class QueryParam<T> {

	/**
	 * 查询条件
	 * 通过where后面追加
	 */
	private final List<T> conditionList;
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
		this.conditionList = new ArrayList<T>();
	}
	
	public List<T> getConditionList() {
		return conditionList;
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
