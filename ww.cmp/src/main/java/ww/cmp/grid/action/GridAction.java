package ww.cmp.grid.action;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ww.cmp.grid.iface.IGridBO;
import ww.cmp.grid.iface.IGridDataBO;
import ww.cmp.grid.pojo.GridOption;
import ww.cmp.grid.pojo.GridParam;
import ww.core.exception.BusiException;
import ww.core.mvc.action.BaseAction;
import ww.core.spring.BeanUtils;

@Controller
@RequestMapping(value="/cmp/grid")
@SuppressWarnings("rawtypes")
public class GridAction extends BaseAction {
	
	private static final String DEFAULT_HANDLER = "";
	
	private IGridBO getGridBO(GridParam param) {
		IGridBO handler = null;
		String handlerName = param.getHandler();
		if(StringUtils.isBlank(handlerName)) {
			handlerName = DEFAULT_HANDLER;
		}
		Object handlerObj = BeanUtils.get(handlerName);
		
		if(handlerObj instanceof IGridBO) {
			handler = (IGridBO) handlerObj;
		} else {
			throw new BusiException("Bean“%s”未实现IGridBO接口！");
		}
		return handler;
	}
	
	private IGridDataBO getGridDataBO(GridParam param) {
		IGridDataBO handler = null;
		String handlerName = param.getHandler();
		if(StringUtils.isBlank(handlerName)) {
			handlerName = DEFAULT_HANDLER;
		}
		Object handlerObj = BeanUtils.get(handlerName);
		
		if(handlerObj instanceof IGridDataBO) {
			handler = (IGridDataBO) handlerObj;
		} else {
			throw new BusiException("Bean“%s”未实现IGridDataBO接口！");
		}
		return handler;
	}
	
	

	@RequestMapping(value="/cfg.do")
	@ResponseBody
	public GridOption getCfg(ServletServerHttpRequest request, ServletServerHttpResponse response,
			@ModelAttribute GridParam param) {
		IGridBO handler = this.getGridBO(param);
		GridOption options = handler.loadOption(param);
		return options;
	}
}
