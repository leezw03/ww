package ww.core.mybatis.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class Pk implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final String table;

	private List<PkCol> colList;
	
	public Pk(String table) {
		this.table = StringUtils.upperCase(table);
		this.colList = new ArrayList<PkCol>();
	}
	
	public Pk(String table, String name, Object value) {
		this(table);
		this.add(name, value);
	}
	
	private Pk add(String name, Object value) {
		this.colList.add(new PkCol(name, value));
		return this;
	}
	
	public List<PkCol> getColList() {
		return colList;
	}

	public String getTable() {
		return table;
	}
	
	public String toString() {
		List<String> colStrList = new ArrayList<String>();
		for(PkCol col : colList) {
			colStrList.add(col.toString());
		}
		return StringUtils.join(colStrList, ";");
	}

	public class PkCol {
		
		private String name;
		
		private Object value;
		
		public PkCol(String name, Object value) {
			this.name = StringUtils.upperCase(name);
			this.value = value;
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
		
		public String toString() {
			return StringUtils.trimToEmpty(this.getName())+"="+this.value != null?this.value.toString():"";
		}
	}
}
