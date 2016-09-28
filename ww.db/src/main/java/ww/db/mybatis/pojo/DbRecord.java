package ww.db.mybatis.pojo;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;

public class DbRecord extends HashMap<String, Object> {

	private static final long serialVersionUID = 1L;

	public Object put(String key, Object value) {
		return super.put(StringUtils.upperCase(key), value);
	}
	
}
