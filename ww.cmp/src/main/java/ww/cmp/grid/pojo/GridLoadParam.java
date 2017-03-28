package ww.cmp.grid.pojo;

import java.io.Serializable;

import ww.core.mvc.pojo.PageParam;
import ww.db.mybatis.pojo.SqlQueryParam;

public class GridLoadParam implements Serializable {

	private static final long serialVersionUID = 1L;

	private GridParam param;
	
	private SqlQueryParam query;
	
	private PageParam page;

	public GridParam getParam() {
		return param;
	}

	public void setParam(GridParam param) {
		this.param = param;
	}

	public SqlQueryParam getQuery() {
		return query;
	}

	public void setQuery(SqlQueryParam query) {
		this.query = query;
	}

	public PageParam getPage() {
		return page;
	}

	public void setPage(PageParam page) {
		this.page = page;
	}
}
