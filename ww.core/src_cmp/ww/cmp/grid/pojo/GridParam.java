package ww.cmp.grid.pojo;

import java.io.Serializable;
import java.util.Map;

public class GridParam implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String id;

	private Map<String, String> options;
	
	private Object queryParams;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<String, String> getOptions() {
		return options;
	}

	public void setOptions(Map<String, String> options) {
		this.options = options;
	}

	public Object getQueryParams() {
		return queryParams;
	}

	public void setQueryParams(Object queryParams) {
		this.queryParams = queryParams;
	}
	
	
}
