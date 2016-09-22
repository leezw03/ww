package ww.core.mybatis.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sun.tools.javac.util.StringUtils;

public class Pk implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final String table;

	private List<PkCol> colList;
	
	public Pk(String table) {
		this.table = StringUtils.toUpperCase(table);
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

	public class PkCol {
		
		private String name;
		
		private Object value;
		
		public PkCol(String name, Object value) {
			this.name = StringUtils.toUpperCase(name);
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
		
	}
}
