package ww.cmp.grid.pojo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class GridOption implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String title;
	
	private List<GridColumn> columns;
	
	private List<GridPlugin> plugins;
	
	private Map<String, Object> attrs;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<GridColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<GridColumn> columns) {
		this.columns = columns;
	}

	public List<GridPlugin> getPlugins() {
		return plugins;
	}

	public void setPlugins(List<GridPlugin> plugins) {
		this.plugins = plugins;
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
	
	public void setAttr(String name, Object attr) {
		this.attrs.put(name, attrs);
	}
}
