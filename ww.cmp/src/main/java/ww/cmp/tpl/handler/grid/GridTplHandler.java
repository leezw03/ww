package ww.cmp.tpl.handler.grid;

import org.dom4j.Element;

import ww.cmp.tpl.handler.AbstractTplHandler;
import ww.cmp.tpl.iface.ITpl;

public class GridTplHandler extends AbstractTplHandler {

	@Override
	public String getPathPattern() {
		return "_GridTpl.xml";
	}

	@Override
	protected String getNodeName() {
		return "grid";
	}

	@Override
	protected ITpl createTplByElement(Element el) {
		return new GridTpl(el);
	}

}
