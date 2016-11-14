package ww.db.mybatis.pojo;

import java.util.List;

public class GenericQueryParam extends QueryParam {

	private String table;
	
	private List<String> columnList;

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public List<String> getColumnList() {
		return columnList;
	}

	public void setColumnList(List<String> columnList) {
		this.columnList = columnList;
	}
}
