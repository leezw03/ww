package ww.core.ibatis.dialect;

public class DerbyDialect extends Dialect {

	public boolean supportsLimitOffset() {
		return true;
	}

	public boolean supportsLimit() {
		return true;
	}

	public String getLimitString(String sql, int offset,
			String offsetPlaceholder, int limit, String limitPlaceholder) {
		if (offset > 0) {
			return sql + " OFFSET " + offsetPlaceholder + " FETCH NEXT " + limitPlaceholder + " ROWS ONLY ";
		} else {
			return sql + " FETCH NEXT " + limitPlaceholder + " ROWS ONLY ";
		}
	}
	

}
