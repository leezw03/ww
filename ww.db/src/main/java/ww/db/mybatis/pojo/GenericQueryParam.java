package ww.db.mybatis.pojo;

import java.util.List;

import ww.db.sql.em.SqlExpression;
import ww.db.sql.em.SqlValueType;
import ww.db.sql.pojo.SqlCondition;

public class GenericQueryParam extends QueryParam<SqlCondition> {

	private String table;
	
	private List<String> columnList;
	
	public void addCondition(SqlCondition condition) {
		this.getConditionList().add(condition);
	}
	
	public void addCondition(String name, Object value, SqlValueType type, SqlExpression expression) {
		this.addCondition(new SqlCondition(name, value, type, expression));
	}
	
	public GenericQueryParam(String table) {
		this.table = table;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public List<String> getColumnList() {
		return columnList;
	}

	public void setColumnList(List<String> columnList) {
		this.columnList = columnList;
	}
}
