package ww.core.mybatis.pojo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.sun.tools.javac.util.StringUtils;

public class Record implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final String table;
	private Pk pk;
	private DbRecord values;
	private Map<String, String> sqlValues;

	public Record(String table) {
		this.table = StringUtils.toUpperCase(table);
		this.values = new DbRecord();
		this.sqlValues = new HashMap<String, String>();
	}
	
	public Record(String table, Pk pk) {
		this.table = table;
		this.pk = pk;
	}
	
	public void setDatas(DbRecord dbRecord) {
		if(dbRecord != null) {
			for(String key : dbRecord.keySet()) {
				this.addData(key, dbRecord.get(key));
			}
		}
	}
	
	public void addData(String name, Object value) {
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

	public Pk getPk() {
		return pk;
	}

	public void setPk(Pk pk) {
		this.pk = pk;
	}

	public DbRecord getValues() {
		return values;
	}

	public Map<String, String> getSqlValues() {
		return sqlValues;
	}
}
