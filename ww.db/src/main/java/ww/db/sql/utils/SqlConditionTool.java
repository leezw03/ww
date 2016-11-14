package ww.db.sql.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;

import ww.core.exception.BusiException;
import ww.core.spring.SysProperty;
import ww.core.utils.DateUtils;
import ww.db.em.DbType;
import ww.db.sql.em.SqlExpression;
import ww.db.sql.em.SqlValueType;
import ww.db.sql.pojo.SqlCondition;

public class SqlConditionTool {

	private volatile static Map<DbType, SqlConditionTool> instanceMap = new ConcurrentHashMap<DbType, SqlConditionTool>();
	
	private DbType dbType;
	
	private String funcToDate = null;
	
	private SqlConditionTool(DbType dbType) {
		this.dbType = dbType;
		funcToDate = SysProperty.getValue("db.cfg.func.todate."+this.dbType);
	}
	
	public static SqlConditionTool getInstance(DbType dbType) {
		SqlConditionTool instance = instanceMap.get(dbType);
		if(instance == null) {
			synchronized(SqlConditionTool.class) {
				if(instance == null) {
					instance = new SqlConditionTool(dbType);
					instanceMap.put(dbType, instance);
				}
			}
		}
		return instance;
	}
	
	public String getConditionText(SqlCondition condition) {
		String conditionText = "";
		String sqlValue = this.getSqlValue(condition.getValue(), condition.getType(), condition.getExpression());
		if(sqlValue != null) {
			if(SqlExpression.append.equals(condition.getExpression())) {
				conditionText = sqlValue;
			} else if(SqlExpression.exists.equals(condition.getExpression())) {
				conditionText = condition.getExpression() + " " + "("+sqlValue+")";
			} else if(SqlExpression.between.equals(condition.getExpression())) {
				conditionText = condition.getName() + sqlValue;
			} else {
				conditionText = condition.getName() + " " + condition.getExpression() + " " + sqlValue;
			}
		}
		return conditionText;
	}
	
	private String getSqlValue(Object value, SqlValueType valueType, SqlExpression expression) {
		if(value == null || StringUtils.isBlank(value.toString())) {
			return null;
		}
		String sqlValue = "";
		if(SqlExpression.between.equals(expression)) {
			Object[] values = null;
			if(value instanceof String) {
				values = StringUtils.split((String) value, ",");
			} else if(value instanceof Object[]) {
				values = (Object[]) value;
			}
			if(values != null) {
				if(values.length == 2) {
					Object leftValue = values[0], rightValue = values[1];
					if(leftValue != null && rightValue != null) {
						sqlValue += SqlExpression.between+" ";
						sqlValue += this.getSqlValue(leftValue, valueType);
						sqlValue += " AND ";
						sqlValue += this.getSqlValue(rightValue, valueType);
					} else if(leftValue == null && rightValue != null) {
						sqlValue += SqlExpression.le + " " + this.getSqlValue(rightValue, valueType);
					} else if(leftValue != null && rightValue == null) {
						sqlValue += SqlExpression.ge + " " + this.getSqlValue(leftValue, valueType);
					}
				} else {
					throw new BusiException("表达式为between的条件值必须包含2个值。");
				}
			}
		} else if(SqlExpression.in.equals(expression)
				|| SqlExpression.notin.equals(expression)) {
			if(value instanceof Object[]) {
				List<String> sqlValueList = new ArrayList<String>();
				Object[] values = (Object[]) value;
				for(Object val : values) {
					sqlValueList.add(this.getSqlValue(val, valueType));
				}
				value = "("+StringUtils.join(sqlValueList, ",")+")";
			}
		} else {
			sqlValue = this.getSqlValue(value, valueType);
		}
		return sqlValue;
	}
	
	private String getSqlValue(Object value, SqlValueType valueType) {
		String sqlValue = "";
		if(value != null) {
			if(valueType.equals(SqlValueType.date)) {
				sqlValue = this.parseDateValue(value);
			} else if(valueType.equals(SqlValueType.datetime)) {
				sqlValue = this.parseDatetimeValue(value);
			} else if(valueType.equals(SqlValueType.string)) {
				sqlValue = this.parseStringValue(value);
			} else {
				sqlValue = value.toString();
			}
		}
		return sqlValue;
	}
	
	private String parseStringValue(Object value) {
		String result = "";
		if(value != null) {
			result = value.toString();
		}
		if(!StringUtils.startsWith(result, "'")) {
			result = "'"+result;
		}
		if(!StringUtils.endsWith(result, "'")) {
			result += "'";
		}
		return result;
	}
	
	private String parseDateValue(Object value) {
		return this.parseDateValue(value, DateUtils.FORMAT_SHORT);
	}
	
	private String parseDatetimeValue(Object value) {
		return this.parseDateValue(value, DateUtils.FORMAT_LONG);
	}
	
	private String parseDateValue(Object value, String format) {
		String result = "";
		String dateStr = "";
		if(value != null) {
			if(value instanceof Date) {
				Date date = (Date) value;
				dateStr = DateUtils.format(date, format);
			} else if(value instanceof String) {
				dateStr = (String) value;
			}
		}
		if(StringUtils.isNotBlank(dateStr)) {
			if(StringUtils.isNotBlank(funcToDate)) {
				result = StringUtils.replace(funcToDate, "{d}", dateStr);
			} else {
				result = "'"+dateStr+"'";
			}
		}
		return result;
	}
}
