package ww.core.ibatis.dialect;

public class InforMixDialect extends Dialect {

	public boolean supportsLimit() {
		return true;
	}

	public boolean supportsLimitOffset() {
		return true;
	}

	public String getLimitString(String sql, int offset,
			String offsetPlaceholder, int limit, String limitPlaceholder) {
		sql = sql.trim();
		boolean isForUpdate = false;
		if (sql.toLowerCase().endsWith(" for update")) {
			sql = sql.substring(0, sql.length() - 11);
			isForUpdate = true;
		}

		StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
		if (offset > 0) {
			pagingSelect
					.append("select skip "+offsetPlaceholder+" first "+limitPlaceholder);
		} else {
			pagingSelect.append("select first "+limitPlaceholder );
		}
		pagingSelect.append(" * from (" +sql +")");
		if (isForUpdate) {
			pagingSelect.append(" for update");
		}

		return pagingSelect.toString();
	}
	

}
