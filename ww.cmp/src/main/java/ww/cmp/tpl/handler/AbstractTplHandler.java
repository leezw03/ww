package ww.cmp.tpl.handler;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

import ww.cmp.tpl.iface.ITpl;
import ww.cmp.tpl.iface.ITplHandler;

@SuppressWarnings("unchecked")
public abstract class AbstractTplHandler implements ITplHandler {
	
	private Logger logger = LoggerFactory.getLogger(AbstractTplHandler.class);

	protected abstract String getNodeName();
	
	protected abstract ITpl createTplByElement(Element el);
	
	@Override
	public List<ITpl> loadTpl(Resource res) {
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
			List<Element> els = root.selectNodes(this.getNodeName());
			for (Element el : els) {
				ITpl tpl = null;
				try {
					tpl = this.createTplByElement(el);
					list.add(tpl);
				} catch(Exception e) {
					e.printStackTrace();
					logger.error(String.format("模版【%s】解析失败，原因：%s。", file, e.getMessage()));
				}
			}
		}
		return list;
	}

}
