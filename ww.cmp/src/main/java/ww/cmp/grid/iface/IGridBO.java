package ww.cmp.grid.iface;

import ww.cmp.grid.pojo.GridOption;
import ww.cmp.grid.pojo.GridParam;

public interface IGridBO<T> extends IGridDataBO<T> {

	public GridOption loadOption(GridParam param);
	
}
