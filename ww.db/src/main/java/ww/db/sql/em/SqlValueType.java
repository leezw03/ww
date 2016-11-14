package ww.db.sql.em;

import org.apache.commons.lang.StringUtils;

public enum SqlValueType {
	
	string("string"),
	number("number"),
	date("date"),
	datetime("datetime");

	private String type;
	
	private SqlValueType(String type) {
		this.type = type;
	}
	
	public String getValue() {
		return StringUtils.isBlank(this.type)?string.getValue():this.type;
	}
}
