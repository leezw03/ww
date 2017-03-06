package ww.cmp.tpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import ww.cmp.tpl.iface.ITpl;
import ww.cmp.tpl.iface.ITplHandler;
import ww.cmp.tpl.pojo.BaseTpl;
import ww.core.spring.SysProperty;

@SuppressWarnings("unchecked")
public class TplLoader {
	
	private static Logger logger = LoggerFactory.getLogger(TplLoader.class);
	
	private volatile static TplLoader instance;
	
	private String[] locations;
	
	public void setLocations(String[] locations) {
		this.locations = locations;
	}
	
	protected List<Resource> resStore;
	
	protected Map<String, Map<String, Object>> tplStore;

	private Map<String, ITplHandler> handlerMap;
	
	private boolean isInited = false;
	
	private boolean devModel = false;
	
	private TplLoader() {
		this.devModel = Boolean.parseBoolean(SysProperty.getValue("cmp.tpl.devmode", "false"));
	}
	
	public static TplLoader getInstance() {
		if(instance == null) {
			synchronized (TplLoader.class) {
				if(instance == null) {
					instance = new TplLoader();
				}
			}
		}
		return instance;
	}

	/**
	 * 初始化配置
	 */
	public void init() {
		this.initTplStore();
	}
	
	private List<Resource> getResources(boolean forceRefresh) {
		if(this.resStore == null) {
			this.resStore = new ArrayList<Resource>();
		}
		if(forceRefresh || !this.isInited) {
			synchronized(this.getClass()) {
				if(this.locations != null) {
					for(String location : locations) {
						Resource[] fs = null;
						try {
							ResourcePatternResolver resourceLoader = new PathMatchingResourcePatternResolver();
							fs = resourceLoader.getResources(location);
						} catch(Exception e) {
							e.printStackTrace();
							logger.error("从路径"+location+"获取文件发生异常！");
							continue;
						}
						this.resStore.addAll(Arrays.asList(fs));
					}
				}
				this.isInited = true;
			}
		}
		return this.resStore;
	}
	
	private void initTplStore() {
		List<Resource> resList = this.getResources(true);
		for(Resource res : resList) {
			String fUriName = null;
			try {
				fUriName = res.getURI().toString();
			} catch(Exception e) {
				e.printStackTrace();
				logger.error("读取配置文件："+fUriName+"失败，请检查配置！");
				continue;
			}
			for(String code : handlerMap.keySet()) {
				ITplHandler handler = handlerMap.get(code);
				Map<String, Object> tplMap = tplStore.get(code);
				if(tplMap == null) {
					tplMap = new HashMap<String, Object>();
					tplStore.put(code, tplMap);
				}
				if(fUriName.indexOf(handler.getPathPattern()) != -1) {
					List<ITpl> addTplList = this.loadTpl(handler, res);
					for(ITpl tpl : addTplList) {
						if(!tplMap.containsKey(tpl.getName())) {
							if(tpl instanceof BaseTpl) {
								BaseTpl baseTpl = (BaseTpl) tpl;
								baseTpl.setFile(fUriName);
							}
							tplMap.put(tpl.getName(), tpl);
						} else {
							tplMap.put(tpl.getName(), tpl);
							logger.warn("覆盖重复的模版：code="+code+"，name="+tpl.getName()+" url="+fUriName);
						}
					}
				}
			}
		}
	}
	
	public <T extends ITplHandler> void registerHandler(String code, Class<T> handlerClass) {
		if(this.handlerMap == null) {
			this.handlerMap = new HashMap<String, ITplHandler>();
		}
		if(this.handlerMap.containsKey(code)) {
			throw new RuntimeException("重复注册ITplHandler："+code+"！");
		}
		T handler;
		try {
			handler = handlerClass.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("ITplHandler："+handlerClass.getName()+"注册失败！");
		}
		logger.info("注册ITplHandler："+code+"["+handlerClass.getName()+"],node="+handler.getNodeName()+",pattern="+handler.getPathPattern());
		this.handlerMap.put(code, handler);
	}
	
	protected List<ITpl> loadTpl(ITplHandler handler, Resource res) {
		List<ITpl> list = new ArrayList<ITpl>();
		String file = null;
		Document doc = null;
		try {
			file = res.getURI().toString();
			SAXReader reader = new SAXReader();
	    	doc = reader.read(res.getInputStream());
		} catch(Exception e) {
			e.printStackTrace();
			String msg = String.format("模版【%s】解析失败，原因：%s。", file, e.getMessage());
			logger.error(msg);
			throw new RuntimeException(msg);
		}
		if(doc != null) {
			Element root = doc.getRootElement();
			List<Element> els = root.selectNodes(handler.getNodeName());
			for (Element el : els) {
				ITpl tpl = null;
				try {
					tpl = handler.createTplByElement(el);
					logger.info("加载tpl模板："+tpl.getName()+"["+file+"]");
					list.add(tpl);
				} catch(Exception e) {
					e.printStackTrace();
					logger.error(String.format("模版【%s】解析失败，原因：%s。", file, e.getMessage()));
				}
			}
		}
		return list;
	}
	
	protected Map<String, Object> getTpls(String code, boolean forceRefresh) {
		if(this.devModel || !this.isInited || forceRefresh) {
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
