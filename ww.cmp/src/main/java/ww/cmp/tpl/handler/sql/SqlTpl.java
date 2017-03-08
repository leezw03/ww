package ww.cmp.tpl.handler.sql;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

import ww.cmp.tpl.pojo.AbstractTpl;

@SuppressWarnings("unchecked")
public class SqlTpl extends AbstractTpl {

	private static final long serialVersionUID = 1L;
	
	private String sqlText;
	
	private List<DynamicItem> dynamicItems;
	
	public SqlTpl(Element el) {
		super(el);
		
		this.sqlText = el.getTextTrim();
		
		dynamicItems = new ArrayList<DynamicItem>();
		List<Element> dynamicItemEls = el.selectNodes("dynamic/item");
		for(Element dynamicItemEl : dynamicItemEls) {
			DynamicItem dynamicItem = new DynamicItem(dynamicItemEl);
			dynamicItems.add(dynamicItem);
		}
	}

	@Override
	protected void toElementExtend(Element el) {
		el.setText(this.sqlText);
		
		if(this.dynamicItems != null) {
			Element dynamicEl = el.addElement("dynamic");
			for(DynamicItem dynamicItem : dynamicItems) {
				Element itemEl = dynamicEl.addElement("item");
				dynamicItem.toElement(itemEl);
			}
		}
	}
	
	public class DynamicItem {
		
		private String name;
		
		private String alias;
		
		private String itemText;
		
		public DynamicItem(Element el) {
			this.name = el.attributeValue("name");
			this.alias = el.attributeValue("alias");
			this.itemText = el.getTextTrim();
		}
		
		public void toElement(Element el) {
			el.addAttribute("name", this.name);
			el.addAttribute("alias", this.alias);
			el.setText(this.itemText);
		}

		public String getName() {
			return name;
		}

		public String getAlias() {
			return alias;
		}

		public String getItemText() {
			return itemText;
		}
	}
}
