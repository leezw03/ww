package ww.db.mybatis.pojo;

import java.io.Serializable;

public class RecordValue implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String TYPE_SQL = "sql";
	public static final String TYPE_COL = "col";

	private String name;
	
	private Object value;
	
	private String type = TYPE_COL;
	
	public RecordValue(String name, Object value) {
		this(name, value, TYPE_COL);
	}
	
	public RecordValue(String name, Object value, String type) {
		this.name = name;
		this.value = value;
		this.type = type;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
