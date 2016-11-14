package ww.db.sql.pojo;

import ww.db.sql.em.SqlExpression;
import ww.db.sql.em.SqlValueType;

/**
 * SQL条件
 * @author leezw
 *
 */
public class SqlCondition {

	/**
	 * 名称
	 */
	private String name;
	/**
	 * 值
	 */
	private Object value;
	/**
	 * 值类型
	 */
	private SqlValueType type;
	/**
	 * 表达式
	 */
	private SqlExpression expression;
	
	public SqlCondition(String name, Object value) {
		this.name = name;
		this.value = value;
		this.type = SqlValueType.string;
	}
	
	public SqlCondition(String name, Object value, SqlValueType type) {
		this(name, value);
		this.type = type;
	}
	
	public SqlCondition(String name, Object value, SqlValueType type, SqlExpression expression) {
		this(name, value, type);
		this.expression = expression;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public SqlValueType getType() {
		return type==null?SqlValueType.string:type;
	}
	public void setType(SqlValueType type) {
		this.type = type;
	}
	public SqlExpression getExpression() {
		return expression==null?SqlExpression.eq:expression;
	}
	public void setExpression(SqlExpression expression) {
		this.expression = expression;
	}
}
