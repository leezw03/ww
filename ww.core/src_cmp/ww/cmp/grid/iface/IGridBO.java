package ww.cmp.grid.iface;

import java.util.List;

import ww.cmp.data.iface.IPageParam;
import ww.cmp.grid.pojo.GridParam;

public interface IGridBO<T extends Object> {

	public List<T> list(IPageParam pageParam, GridParam gridParam);
	
	public long count(IPageParam pageParam, GridParam gridParam);
	
}
