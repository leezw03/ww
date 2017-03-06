package ww.cmp.tpl.handler.grid;

import org.dom4j.Element;

import ww.cmp.tpl.iface.ITpl;
import ww.cmp.tpl.iface.ITplHandler;

public class GridTplHandler implements ITplHandler {

	@Override
	public String getPathPattern() {
		return "_GridTpl.xml";
	}

	@Override
	public String getNodeName() {
		return "grid";
	}

	@Override
	public ITpl createTplByElement(Element el) {
		return new GridTpl(el);
	}

}
