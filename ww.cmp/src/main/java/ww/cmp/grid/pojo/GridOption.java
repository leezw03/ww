package ww.cmp.grid.pojo;

import java.util.HashMap;
import java.util.Map;

public class GridOption {

	private String handler;
	
	private Map<String, Object> attrs;
	
	public GridOption() {
		attrs = new HashMap<String, Object>();
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
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
	
	public void setAttr(String name, Object value) {
		this.attrs.put(name, value);
	}
}
