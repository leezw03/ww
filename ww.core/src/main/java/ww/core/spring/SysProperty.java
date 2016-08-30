package ww.core.spring;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import ww.Ww;

public class SysProperty extends PropertyPlaceholderConfigurer {
	
	private static final Logger logger = LoggerFactory.getLogger(Ww.Log.CORE);

	private static volatile SysProperty instance;
	
	private ResourcePatternResolver resourceLoader;
	
	private Properties appProp;
	
	private Properties sysProp;
	
	private Properties envProp;
	
	private Properties prop;
	
	private SysProperty() {
		resourceLoader = new PathMatchingResourcePatternResolver();
	}
	
	public static SysProperty getInstance() {
		if (instance == null) {
			synchronized (SysProperty.class) {
				if (instance == null) {
					instance = new SysProperty();
				}
			}
		}
		return instance;
	}
	
	public static String getValue(String key) {
		return SysProperty.getValue(key, "");
	}
	
	public static String getValue(String key, String defaultValue) {
		SysProperty instance = SysProperty.getInstance();
		String value = instance.prop.getProperty(key, defaultValue);
		return value;
	}
	
	@Override
	protected Properties mergeProperties() throws IOException {
		prop = super.mergeProperties();
		this.initAppProp();
		this.initSysProp();
		this.initEnvProp();
		return prop;
	}
	
	private void initAppProp() throws IOException {
		Resource res = resourceLoader.getResource(Ww.Env.APP);
		appProp = new Properties();
		appProp.load(res.getInputStream());
	}
	
	private void initSysProp() throws IOException {
		sysProp = new Properties();
		this.loadProp(sysProp, Ww.Env.SYS+"*.properties");
	}
	
	private void initEnvProp() throws IOException {
		String env = appProp.getProperty("env", "default");
		String envPath = appProp.getProperty("env.path");
		
		String propPath = null;
		if(StringUtils.isNotBlank(envPath)) {
			propPath = "file:"+envPath+File.separator+"user"+File.separator+env+".properties";
		} else {
			propPath = Ww.Env.ENV+env+".properties";
		}
		envProp = new Properties();
		this.loadProp(envProp, propPath);
	}
	
	private void loadProp(Properties custProp, String path) throws IOException {
		Resource[] resArray = resourceLoader.getResources(path);
		for (Resource location : resArray) {
			Properties p = new Properties();
			logger.debug(String.format("加载配置文件：%s", location.getFilename()));
			p.load(new InputStreamReader(location.getInputStream(), "UTF-8"));
			for (Object k : p.keySet()) {
				String pk = k.toString();
				if (custProp.containsKey(pk)) {
					logger.warn(String.format("重复配置:%s，新值：%s，覆盖原值：%s。", pk, p.getProperty(pk), custProp.getProperty(pk)));
				}
				custProp.setProperty(pk, p.getProperty(pk));
				prop.setProperty(pk, p.getProperty(pk));
			}
		}
	}
}
