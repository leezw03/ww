package ww.cmp.tpl.pojo;

import org.dom4j.Element;

import ww.cmp.tpl.iface.ITpl;

public class BaseTpl implements ITpl {

	private static final long serialVersionUID = 1L;
	/**
	 * 配置文件位置
	 */
	private String file;
	/**
	 * 配置名称
	 */
	protected String name;
	
	public BaseTpl(Element el) {
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
	
	public void merge(BaseTpl merge) {
		if(merge.name != null) {
			this.name = merge.name;
		}
		if(merge.file != null) {
			this.file = merge.file;
		}
	}
	
	public void addTpl2Element(Element el) {
		if(this.name != null) {
			el.addAttribute("name", this.name);
		}
	}
}
