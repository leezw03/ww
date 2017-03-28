package ww.db.mybatis.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ww.db.sql.em.SqlExpression;
import ww.db.sql.em.SqlValueType;
import ww.db.sql.pojo.SqlCondition;

public class SqlQueryParam {
	
	/**
	 * 查询条件
	 * 通过where后面追加
	 */
	private final List<SqlCondition> conditionList;
	/**
	 * 变量参数
	 * 通过替换sql中的关键词
	 */
	private Map<String, Object> varMap;
	/**
	 * 排序
	 */
	private String sort;
	
	public SqlQueryParam() {
		this.conditionList = new ArrayList<SqlCondition>();
	}
	
	public List<SqlCondition> getConditionList() {
		return conditionList;
	}
	public Map<String, Object> getVarMap() {
		return varMap;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	
	public void setVar(String key, Object value) {
		if(this.varMap == null) {
			this.varMap = new HashMap<String, Object>();
		}
		this.getVarMap().put(key, value);
	}
	
	public Object getVar(String key) {
		Object var = null;
		if(this.varMap != null) {
			var = this.varMap.get(key);
		}
		return var;
	}
	
	public void addCondition(SqlCondition condition) {
		conditionList.add(condition);
	}
	
	public void addCondition(String name, Object value, SqlValueType type, SqlExpression expression) {
		this.addCondition(new SqlCondition(name, value, type, expression));
	}
	
}
