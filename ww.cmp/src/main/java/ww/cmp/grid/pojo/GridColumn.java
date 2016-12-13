package ww.cmp.grid.pojo;

import java.io.Serializable;
import java.util.Map;

public class GridColumn implements Serializable {

	private static final long serialVersionUID = 1L;

	private String header;
	
	private String dataIndex;
	
	private int width = 100;
	
	private boolean hidden = true;
	
	private boolean sortable = true;
	
	private Map<String, Object> attrs;

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getDataIndex() {
		return dataIndex;
	}

	public void setDataIndex(String dataIndex) {
		this.dataIndex = dataIndex;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public boolean isSortable() {
		return sortable;
	}

	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}

	public Map<String, Object> getAttrs() {
		return attrs;
	}

	public void setAttrs(Map<String, Object> attrs) {
		this.attrs = attrs;
	}
	
	public Object getAttr(String name) {
		return this.attrs.get(name);
	}
}
