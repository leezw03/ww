package ww.cmp.tpl.pojo;

import java.io.Serializable;

import org.dom4j.Element;

import ww.cmp.tpl.iface.ITpl;

public abstract class AbstractTpl implements Serializable, ITpl {

	private static final long serialVersionUID = 1L;
	
	protected String name;
	
	protected Element el;
	
	protected String file;
	
	public AbstractTpl(Element el) {
		this.el = el;
		if(el != null) {
			this.name = el.attributeValue("name");
		}
	}
	
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getName() {
		return name;
	}
	public Element getEl() {
		return el;
	}

	@Override
	public void toElement(Element el) {
		if(this.name != null) {
			el.addAttribute("name", this.name);
		}
		this.toElementExtend(el);
	}
	
	protected abstract void toElementExtend(Element el);
}
