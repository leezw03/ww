package ww.core.spring;

import ww.core.exception.BusiException;

public class BeanUtils {
	
	public static Object get(String beanId) {
		Object bean = null;
		if(BeanUtils.exists(beanId)) {
			bean = SpringContextHolder.getContext().getBean(beanId);
		} else {
			throw new BusiException(String.format("未找到id=%s的bean定义！", beanId));
		}
		return bean;
	}
	
	public static boolean exists(String beanId) {
		return SpringContextHolder.getContext().containsBean(beanId);
	}
}
