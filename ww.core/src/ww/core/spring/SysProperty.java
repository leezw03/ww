package ww.core.spring;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import ww.core.WW;
import ww.core.exception.InnerException;

public class SysProperty extends PropertyPlaceholderConfigurer {
	
	private static final Logger logger = LoggerFactory.getLogger(WW.LOG_CORE);

	private static volatile SysProperty instance;
	
	private Resource[] custLocations;

	public void setCustLocations(Resource[] custLocations) {
		this.custLocations = custLocations;
	}
	
	private Properties appProp;
	
	private Properties sysProp;
	
	private Properties prop;
	
	private SysProperty() {
		
	}
	
	public SysProperty getInstance() {
		if (instance == null) {
			synchronized (SysProperty.class) {
				if (instance == null) {
					instance = new SysProperty();
				}
			}
		}
		return instance;
	}
	
	@Override
	protected Properties mergeProperties() throws IOException {
		prop = super.mergeProperties();
		this.initAppProp();
		this.initSysProp();
		return prop;
	}
	
	private void initAppProp() {
		try {
			ResourcePatternResolver resourceLoader = new PathMatchingResourcePatternResolver();
			Resource res = resourceLoader.getResource(WW.PROP_PATH_APP);
			
			appProp = new Properties();
			appProp.load(res.getInputStream());
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new InnerException(String.format("配置环境配置文件 %s 加载失败！", WW.PROP_PATH_APP), e);
		}
	}
	
	private void initSysProp() {
		List<Properties> propList = new ArrayList<Properties>();
		if (this.custLocations != null) {
			for (Resource location : this.custLocations) {
				Properties p = new Properties();
				try {
					logger.debug(String.format("加载系统配置文件：%s", location.getFilename()));
					p.load(new InputStreamReader(location.getInputStream(), "UTF-8"));
				} catch(Exception e) {
					e.printStackTrace();
					throw new InnerException(String.format("系统配置文件 %s 加载失败！", location.getFilename()), e);
				}
				propList.add(p);
			}
		}
		for (Properties p : propList) {
			for (Object k : p.keySet()) {
				String pk = k.toString();
				if (sysProp.containsKey(pk)) {
					logger.warn(String.format("重复配置:%s，新值：%s，覆盖原值：%s。", pk, p.getProperty(pk), sysProp.getProperty(pk)));
				}
				sysProp.setProperty(pk, p.getProperty(pk));
				prop.setProperty(pk, p.getProperty(pk));
			}
		}
	}
}
