package ww.cmp.tpl.handler.grid;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Element;

import ww.cmp.tpl.handler.sql.SqlTpl;
import ww.cmp.tpl.pojo.AbstractTpl;

@SuppressWarnings("unchecked")
public class GridTpl extends AbstractTpl {

	private static final long serialVersionUID = 1L;
	
	private String title;
	
	private List<Column> columns;
	
	private Map<String, String> attributes;
	
	private SqlTpl sqlTpl;
	
	public GridTpl(Element el) {
		super(el);
		
		this.title = el.attributeValue("title");
		this.attributes = new LinkedHashMap<String, String>();
		for(Object obj : el.attributes()) {
			Attribute attr = (Attribute) obj;
			if (attr != null) {
				this.attributes.put(attr.getName(), attr.getValue());
			}
		}
		
		this.columns = new ArrayList<Column>();
		List<Element> columnEls = el.selectNodes("columns/column");
		for (Element columnEl : columnEls) {
			Column column = new Column(columnEl);
			this.columns.add(column);
		}
		
		Element sqlEl = el.element("sql");
		if(sqlEl != null) {
			this.sqlTpl = new SqlTpl(sqlEl);
		}
	}
	
	public String getTitle() {
		return title;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public SqlTpl getSqlTpl() {
		return sqlTpl;
	}

	public class Column {
		
		private String header;
		
		private String dataIndex;
		
		private Map<String, String> attributes;
		
		private String content;
		
		public Column(Element el) {
			this.header = el.attributeValue("header");
			this.dataIndex = el.attributeValue("dataIndex");
			
			this.attributes = new LinkedHashMap<String, String>();
			for(Object obj : el.attributes()) {
				Attribute attr = (Attribute) obj;
				if (attr != null) {
					this.attributes.put(attr.getName(), attr.getValue());
				}
			}
			
			this.content = el.getTextTrim();
		}

		public String getHeader() {
			return header;
		}

		public String getDataIndex() {
			return dataIndex;
		}

		public Map<String, String> getAttributes() {
			return attributes;
		}

		public String getContent() {
			return content;
		}
	}

	@Override
	public void toElementExtend(Element el) {
		if(this.attributes != null) {
			for(String key : this.attributes.keySet()) {
				el.addAttribute(key, this.attributes.get(key));
			}
		}
		
		if(this.columns != null) {
			Element columnsEl = el.addElement("columns");
			for(Column column : this.columns) {
				Element columnEl = columnsEl.addElement("column");
				if(column.attributes != null) {
					for(String key : column.attributes.keySet()) {
						columnEl.addAttribute(key, column.attributes.get(key));
					}
				}
			}
		}
		
		if(this.sqlTpl != null) {
			Element sqlEl = el.addElement("sql");
			this.sqlTpl.toElement(sqlEl);
		}
	}

}
