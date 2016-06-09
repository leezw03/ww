package ww.cmp.grid.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ww.cmp.data.iface.IPageParam;
import ww.cmp.grid.iface.IGridBO;
import ww.cmp.grid.pojo.GridParam;
import ww.core.exception.InnerException;
import ww.core.spring.BeanUtils;

@Controller
@RequestMapping(value = "/GridAction")
@SuppressWarnings("unchecked")
public class GridAction {

	private IGridBO<Object> getGridBO(GridParam gridParam) {
		IGridBO<Object> bo = null;
		Map<String, String> options = gridParam.getOptions();
		if(options != null) {
			String gridBO = options.get("gridBO");
			if(StringUtils.isNotBlank(gridBO)) {
				bo = (IGridBO<Object>) BeanUtils.getBean(gridBO);
			}
		}
		if(bo == null) {
			throw new InnerException("获取gridBO失败！");
		}
		return bo;
	}
	
	@RequestMapping(value = "/list")
	@ResponseBody
	public List<Object> list(HttpServletRequest request, HttpServletResponse response,
			IPageParam pageParam, GridParam gridParam) {
		IGridBO<Object> bo = this.getGridBO(gridParam);
		return bo.list(pageParam, gridParam);
	}
	
	@RequestMapping(value = "/count")
	@ResponseBody
	public long count(HttpServletRequest request, HttpServletResponse response,
			IPageParam pageParam, GridParam gridParam) {
		IGridBO<Object> bo = this.getGridBO(gridParam);
		return bo.count(pageParam, gridParam);
	}
}
