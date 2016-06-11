package ww.core.utils.data;

import java.sql.Clob;
import java.sql.SQLException;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;

public class DataUtils {
	
	public static String clob2String(Clob clob) {
		try {
			return (clob != null ? clob.getSubString(1, (int) clob.length()) : null);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getStringValue(Map<String, Object> map, String key) {
		if(map == null) return null;
		
		Object value = map.get(key);
		if (value == null) {
			return null;
		}
		if (value instanceof String) {
			return StringUtils.trimToEmpty((String) value);
		} else if (value instanceof Clob) {
			return clob2String((Clob) value);
		} else if (value instanceof JSONObject) {
			return ((JSONObject)value).getString("value");
		} else {
			return value.toString();
		}
	}
}
