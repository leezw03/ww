package ww.cmp.tpl.handler.sql;

import org.dom4j.Element;

import ww.cmp.tpl.handler.AbstractTplHandler;
import ww.cmp.tpl.iface.ITpl;

public class SqlTplHandler extends AbstractTplHandler {

	@Override
	public String getPathPattern() {
		return "_SqlTpl.xml";
	}

	@Override
	protected String getNodeName() {
		return "sql";
	}

	@Override
	protected ITpl createTplByElement(Element el) {
		return new SqlTpl(el);
	}

}
