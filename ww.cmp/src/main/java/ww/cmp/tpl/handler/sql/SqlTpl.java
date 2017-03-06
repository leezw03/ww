package ww.cmp.tpl.handler.sql;

import java.util.List;
import java.util.Map;

import org.dom4j.Element;

import ww.cmp.tpl.pojo.BaseTpl;

public class SqlTpl extends BaseTpl {

	private static final long serialVersionUID = 1L;
	
	private String text;
	
	private Map<String, String> authSqlMap;

	public SqlTpl(Element el) {
		super(el);
		
		List<Element> authSqlElList = el.selectNodes("authSqls/sql");
		for(Element authSqlEl : authSqlElList) {
			AuthSql authSql = new AuthSql(authSqlEl);
		}
	}
	
	private class AuthSql {
		
		public AuthSql(Element el) {
			
		}
	}
}
