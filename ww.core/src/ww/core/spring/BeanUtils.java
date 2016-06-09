package ww.core.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import ww.core.exception.InnerException;

public class BeanUtils implements ApplicationContextAware {
	
	private static volatile ApplicationContext ctx;

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		ctx = context;
	}

	public static Object getBean(String beanName) {
		Object bean = null;
		if(ctx.containsBean(beanName)) {
			bean = ctx.getBean(beanName);
		} else {
			throw new InnerException(String.format("Bean【%s】未注册！", beanName));
		}
		return bean;
	}
	
}
