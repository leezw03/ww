package ww.cmp.tpl.handler.sql;

import org.dom4j.Element;

import ww.cmp.tpl.TplLoader;
import ww.cmp.tpl.iface.ITpl;
import ww.cmp.tpl.iface.ITplHandler;

public class SqlTplHandler implements ITplHandler {

	static {
		TplLoader.getInstance().registerHandler("sql", SqlTplHandler.class);
	}
	
	@Override
	public String getPathPattern() {
		return "_SqlTpl.xml";
	}

	@Override
	public String getNodeName() {
		return "sql";
	}

	@Override
	public ITpl createTplByElement(Element el) {
		return new SqlTpl(el);
	}

}
