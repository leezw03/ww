package ww.core.mvc.pojo;

/**
 * 基本查询条件
 * @author lizhiwei
 *
 */
public class BaseQueryCondition implements IQueryCondition {

	/**
	 * 条件名
	 */
	private String name;
	/**
	 * 条件值
	 */
	private Object value;
	/**
	 * 表达式
	 */
	private String expression;
	
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
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	
}
