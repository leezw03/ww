package ww.cmp.tpl.iface;

import org.dom4j.Element;

public interface ITplHandler {
	
	String getNodeName();

	String getPathPattern();
	
	ITpl createTplByElement(Element el);
	
}
