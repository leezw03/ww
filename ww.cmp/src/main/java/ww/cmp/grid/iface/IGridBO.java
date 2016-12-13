package ww.cmp.grid.iface;

import java.util.List;

import ww.cmp.grid.pojo.GridParam;
import ww.cmp.grid.pojo.PageResult;
import ww.core.mvc.pojo.PageParam;
import ww.db.mybatis.pojo.QueryParam;

public interface IGridBO<T> {

	public List<T> load(GridParam param, QueryParam query, PageParam page);
	
	public PageResult page(GridParam param, QueryParam query, PageParam page);
	
}
