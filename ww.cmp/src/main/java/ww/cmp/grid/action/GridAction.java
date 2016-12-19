package ww.cmp.grid.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import ww.cmp.grid.iface.IGridBO;
import ww.cmp.grid.iface.IGridDataBO;
import ww.cmp.grid.pojo.GridLoadParam;
import ww.cmp.grid.pojo.GridOption;
import ww.cmp.grid.pojo.GridParam;
import ww.core.mvc.action.BaseAction;
import ww.core.mvc.pojo.PageParam;
import ww.core.mvc.pojo.PageResult;
import ww.core.spring.BeanUtils;
import ww.db.mybatis.pojo.QueryParam;

@Controller
@RequestMapping(value="/cmp/grid")
@SuppressWarnings("rawtypes")
public class GridAction extends BaseAction {
	
	private static final String DEFAULT_HANDLER = "";
	
	private IGridBO getGridBO(GridParam param) {
		String handlerName = param.getHandler();
		if(StringUtils.isBlank(handlerName)) {
			handlerName = DEFAULT_HANDLER;
		}
		return BeanUtils.get(handlerName, IGridBO.class);
	}
	
	private IGridDataBO getGridDataBO(GridParam param) {
		String handlerName = param.getHandler();
		if(StringUtils.isBlank(handlerName)) {
			handlerName = DEFAULT_HANDLER;
		}
		return BeanUtils.get(handlerName, IGridDataBO.class);
	}

	@RequestMapping(value="/loadOption")
	@ResponseBody
	public GridOption loadOption(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="loadParam") String loadParamStr) {
		GridLoadParam loadParam = JSON.parseObject(loadParamStr, GridLoadParam.class);
		GridParam param = loadParam.getParam();
		IGridBO handler = this.getGridBO(param);
		GridOption options = handler.loadOption(param);
		return options;
	}
	
	@RequestMapping(value="/loadData")
	@ResponseBody
	public List loadData(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="loadParam") String loadParamStr) {
		GridLoadParam loadParam = JSON.parseObject(loadParamStr, GridLoadParam.class);
		GridParam param = loadParam.getParam();
		QueryParam query = loadParam.getQuery();
		PageParam page = loadParam.getPage();
		IGridDataBO handler = this.getGridDataBO(param);
		return handler.loadData(param, query, page);
	}
	
	@RequestMapping(value="/loadPage")
	@ResponseBody
	public PageResult loadPage(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="loadParam") String loadParamStr) {
		GridLoadParam loadParam = JSON.parseObject(loadParamStr, GridLoadParam.class);
		GridParam param = loadParam.getParam();
		QueryParam query = loadParam.getQuery();
		PageParam page = loadParam.getPage();
		IGridDataBO handler = this.getGridDataBO(param);
		return handler.loadPage(param, query, page);
	}
	
}
