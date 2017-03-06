package ww.cmp.tpl.iface;

import java.io.Serializable;

import org.dom4j.Element;

public interface ITpl extends Serializable {

	String getFile();
	
	String getName();
	
	void toElement(Element el);
	
}
