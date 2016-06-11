package ww.core.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import ww.core.utils.http.HttpRequestDeviceUtils;

public class CustomSimpleMappingExceptionResolver extends
		SimpleMappingExceptionResolver {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("ex", ex);
		model.put("time", (new Date()).getTime());
		model.put("msg", ex.getMessage());
		
		logger.error(ex.getMessage(), ex);
		
		String xRequestedWith = request.getHeader("X-Requested-With");
		if (!StringUtils.isEmpty(xRequestedWith)) {
			return new ModelAndView("core/app/commons/exception/ajax-error", model);
		} else {
			// 根据不同错误转向不同页面
			if (ex instanceof InnerException) {
				if(HttpRequestDeviceUtils.isMobileDevice(request)) {
					return new ModelAndView("core/app/commons/exception/business", model);
				} else {
					return new ModelAndView("core/commons/exception/business", model);
				}
			} else {
				if(HttpRequestDeviceUtils.isMobileDevice(request)) {
					return new ModelAndView("core/app/commons/exception/error", model);
				} else {
					return new ModelAndView("core/commons/exception/error", model);
				}
			}
		}
	}

}
