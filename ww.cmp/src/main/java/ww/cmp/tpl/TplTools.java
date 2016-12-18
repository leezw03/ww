package ww.cmp.tpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import ww.cmp.tpl.iface.ITpl;
import ww.cmp.tpl.iface.ITplHandler;
import ww.cmp.tpl.pojo.BaseTpl;

public class TplTools {
	
	private static Logger logger = LoggerFactory.getLogger(TplTools.class);
	
	public static Map<String, Map<String, Object>> getTpl(List<String> locations, Map<String, ITplHandler> handlers) {
		Map<String, Map<String, Object>> allTplMap = new HashMap<String, Map<String, Object>>();
		for(String loc : locations) {
			Resource[] fs = null;
			try {
				ResourcePatternResolver resourceLoader = new PathMatchingResourcePatternResolver();
				fs = resourceLoader.getResources(loc);
			} catch(Exception e) {
				e.printStackTrace();
				logger.error("从路径"+loc+"获取文件发生异常！");
				continue;
			}
			for (Resource f : fs) {
				String fUriName = null;
				try {
					fUriName = f.getURI().toString();
					for(String code : handlers.keySet()) {
						ITplHandler handler = handlers.get(code);
						Map<String, Object> tplMap = allTplMap.get(code);
						if(tplMap == null) {
							tplMap = new HashMap<String, Object>();
							allTplMap.put(code, tplMap);
						}
						if(fUriName.indexOf(handler.getPathPattern()) != -1) {
							List<ITpl> addTplList = handler.loadTpl(f);
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
				} catch(Exception e) {
					e.printStackTrace();
					logger.error("读取配置文件："+fUriName+"失败，请检查配置！");
					throw new RuntimeException(e.getMessage());
				}
			}
		}
		return allTplMap;
	}
}
