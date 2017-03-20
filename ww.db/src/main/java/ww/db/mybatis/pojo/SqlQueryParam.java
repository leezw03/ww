package ww.db.mybatis.pojo;

import ww.db.sql.em.SqlExpression;
import ww.db.sql.em.SqlValueType;
import ww.db.sql.pojo.SqlCondition;

public class SqlQueryParam extends QueryParam<SqlCondition> {
	
	public void addCondition(String name, Object value, SqlValueType type, SqlExpression expression) {
		this.addCondition(new SqlCondition(name, value, type, expression));
	}
	
}
