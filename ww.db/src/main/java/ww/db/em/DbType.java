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
}
