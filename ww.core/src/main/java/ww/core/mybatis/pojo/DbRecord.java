package ww.core.mybatis.pojo;

import java.util.HashMap;

import com.sun.tools.javac.util.StringUtils;

public class DbRecord extends HashMap<String, Object> {

	private static final long serialVersionUID = 1L;

	public Object put(String key, Object value) {
		return super.put(StringUtils.toUpperCase(key), value);
	}
	
}
