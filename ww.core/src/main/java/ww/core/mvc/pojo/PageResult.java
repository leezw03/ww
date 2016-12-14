package ww.core.mvc.pojo;

import ww.core.mvc.pojo.PageParam;

public class PageResult extends PageParam {

	private static final long serialVersionUID = 1L;
	
	private int total = 0;

	public PageResult(int start) {
		super(start);
	}
	
	public PageResult(int start, int limit) {
		super(start, limit);
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	
}
