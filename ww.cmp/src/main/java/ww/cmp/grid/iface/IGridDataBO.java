package ww.cmp.grid.iface;

import java.util.List;

import ww.cmp.grid.pojo.GridParam;
import ww.core.mvc.pojo.PageParam;
import ww.core.mvc.pojo.PageResult;
import ww.db.mybatis.pojo.QueryParam;

public interface IGridDataBO<T> {

	public List<T> loadData(GridParam param, QueryParam query, PageParam page);
	
	public PageResult loadPage(GridParam param, QueryParam query, PageParam page);
	
}
