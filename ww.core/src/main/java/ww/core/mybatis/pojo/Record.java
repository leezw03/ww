package ww.core.mybatis.pojo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Record implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final String table;
	private DbRecord values;
	private Map<String, String> sqlValues;

	public Record(String table) {
		this.table = table;
	}
	
	public void setDatas(DbRecord dbRecord) {
		if(dbRecord != null) {
			for(String key : dbRecord.keySet()) {
				this.addData(key, dbRecord.get(key));
			}
		}
	}
	
	public void addData(String name, Object value) {
		if(this.values == null) {
			this.values = new DbRecord();
		}
		this.values.put(name, value);
	}
	
	public void addSqlData(String name, String sql) {
		if(this.sqlValues == null) {
			this.sqlValues = new HashMap<String, String>();
		}
		this.sqlValues.put(name, sql);
	}

	public String getTable() {
		return table;
	}
}
