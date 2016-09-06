package ww.core.mvc.pojo;

import java.io.Serializable;

public class PageParam implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int start = 0;
	
	private int limit = 20;
	
	public PageParam(int start) {
		this.start = start;
	}
	
	public PageParam(int start, int limit) {
		this.start = start;
		this.limit = limit;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

}
