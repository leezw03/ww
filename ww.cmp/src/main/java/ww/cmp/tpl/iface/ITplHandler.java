package ww.cmp.tpl.iface;

import java.util.List;

import org.springframework.core.io.Resource;

public interface ITplHandler {

	public String getPathPattern();
	
	public List<ITpl> loadTpl(Resource res);
	
}
