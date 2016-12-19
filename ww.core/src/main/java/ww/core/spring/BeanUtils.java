package ww.core.spring;

import java.lang.reflect.Type;

import ww.core.exception.BusiException;

@SuppressWarnings("unchecked")
public class BeanUtils {
	
	public static <T> T get(String beanId, Type clazz) {
		T bean = null;
		if(BeanUtils.exists(beanId)) {
			bean = (T)SpringContextHolder.getContext().getBean(beanId);
		} else {
			throw new BusiException(String.format("未找到id=%s的bean定义！", beanId));
		}
		return bean;
	}
	
	public static boolean exists(String beanId) {
		return SpringContextHolder.getContext().containsBean(beanId);
	}
}
