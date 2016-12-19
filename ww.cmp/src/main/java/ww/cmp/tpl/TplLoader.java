package ww.cmp.tpl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang.StringUtils;

import ww.cmp.tpl.iface.ITplHandler;
import ww.core.spring.BeanUtils;
import ww.core.spring.SysProperty;

public class TplLoader {
	
	private String[] locations;
	
	public void setLocations(String[] locations) {
		this.locations = locations;
	}
	
	protected Map<String, Map<String, Object>> tplStore;

	private Lock lock = new ReentrantLock();
	
	private boolean isInited = false;
	
	private Map<String, ITplHandler> handlers;

	public TplLoader() {
		tplStore = new HashMap<String, Map<String, Object>>();
		handlers = new HashMap<String, ITplHandler>();
		String types = SysProperty.getValue("cmp.tpl.types");
		if(StringUtils.isNotBlank(types)) {
			String[] typeArray = StringUtils.split(types, ",");
			for(String type : typeArray) {
				if(StringUtils.isNotBlank(type)) {
					String handlerName = SysProperty.getValue("cmp.tpl.types."+type);
					if(StringUtils.isNotBlank(handlerName)) {
						handlers.put(type, BeanUtils.get(handlerName, ITplHandler.class));
					}
				}
			}
		}
	}
	/**
	 * 启动时初始化
	 */
	public void start() {
		this.init();
	}
	
	/**
	 * 初始化配置
	 */
	public void init() {
		this.lock.lock();
		try {
			this.isInited = false;
			tplStore.clear();
			Map<String, Map<String, Object>> cacheMap = TplTools.getTpl(Arrays.asList(locations), handlers);
			tplStore.putAll(cacheMap);
			this.isInited = true;
		} finally {
			this.lock.unlock();
		}
	}
	
	protected Map<String, Object> getTpls(String code, boolean forceLoad) {
		boolean devModel = Boolean.parseBoolean(SysProperty.getValue("cmp.tpl.devmode", "false"));
		if (devModel || !this.isInited || forceLoad) {
			this.init();
		}
		return tplStore.get(code);
	}
	
	protected Map<String, Object> getTpls(String code) {
		return this.getTpls(code, false);
	}
	
	protected Object getTpl(String code, String name) {
		Object obj = null;
		Map<String, Object> tpls = this.getTpls(code);
		if(tpls!=null && StringUtils.isNotBlank(name)) {
			obj = tpls.get(name);
		}
		return obj;
	}
	
}
