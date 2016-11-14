package ww.db.em;

public enum DbType {
	
	oracle("oracle"),
	mysql("mysql"),
	derby("derby");
	
	private String type;
	
	private DbType(String type) {
		this.type = type;
	}
	
	public String getValue() {
		return this.type;
	}
	
	public static DbType get(String type) {
		DbType dbType = null;
		if(DbType.oracle.equals(type)) {
			dbType = DbType.oracle;
		} else if(DbType.mysql.equals(type)) {
			dbType = DbType.mysql;
		} else if(DbType.derby.equals(type)) {
			dbType = DbType.derby;
		}
		return dbType;
	}
}
