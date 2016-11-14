package ww.db.sql.em;

public enum SqlExpression {
	
	//基础表达式
	eq("="),
	gt(">"), 
	ge(">="), 
	lt("<"), 
	le("<="), 
	in("IN"), 
	notin("NOT IN"), 
	like("LIKE"),
	between("BETWEEN"),
	is("IS"),
	
	//特殊表达式
	exists("EXISTS"),
	append("APPEND");
	
	private String expression;
	
	private SqlExpression(String expression) {
		this.expression = expression;
	}
	
	public String toString() {
		return this.expression;
	}
}
