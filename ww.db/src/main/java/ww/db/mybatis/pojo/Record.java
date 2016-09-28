package ww.db.mybatis.pojo;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class Record implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Pk pk;
	private final Map<String, RecordValue> values;
	
	private Record() {
		this.values = new LinkedHashMap<String, RecordValue>();
	}

	public Record(String table) {
		this();
		this.pk = new Pk(table);
	}
	
	public Record(Pk pk) {
		this();
		this.pk = pk;
	}
	
	public void setDatas(Map<String, Object> map) {
		if(map != null) {
			for(String key : map.keySet()) {
				this.addData(key, map.get(key));
			}
		}
	}
	
	public void addData(String name, Object value) {
		RecordValue recordValue = new RecordValue(name, value);
		this.values.put(name, recordValue);
	}
	
	public void addSqlData(String name, String sql) {
		RecordValue recordValue = new RecordValue(name, sql, RecordValue.TYPE_SQL);
		this.values.put(name, recordValue);
	}
	
	public void removeData(String name) {
		this.values.remove(name);
	}
	
	public void clear() {
		this.values.clear();
	}
	
	public String getTable() {
		return this.getPk().getTable();
	}

	public Pk getPk() {
		return pk;
	}

	public void setPk(Pk pk) {
		this.pk = pk;
	}

	public Map<String, RecordValue> getValues() {
		return values;
	}

}
