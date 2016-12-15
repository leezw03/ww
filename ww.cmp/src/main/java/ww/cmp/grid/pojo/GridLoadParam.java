package ww.cmp.grid.pojo;

import java.io.Serializable;

import ww.db.mybatis.pojo.QueryParam;

public class GridLoadParam implements Serializable {

	private static final long serialVersionUID = 1L;

	private GridParam param;
	
	private QueryParam query;

	public GridParam getParam() {
		return param;
	}

	public void setParam(GridParam param) {
		this.param = param;
	}

	public QueryParam getQuery() {
		return query;
	}

	public void setQuery(QueryParam query) {
		this.query = query;
	}
}
