package ww.core.utils.database;


public class DbType {
    private String value;

    public static final String _DB_TYPE_ORACLE    = "oracle";
    public static final String _DB_TYPE_HSQL      = "hsql";
    public static final String _DB_TYPE_INFORMIX  = "informix";
    public static final String _DB_TYPE_MYSQL  = "mysql";
    public static final String _DB_TYPE_DERBY  = "derby";


    public static final DbType DB_TYPE_ORACLE = new DbType(_DB_TYPE_ORACLE);
    public static final DbType DB_TYPE_MYSQL = new DbType(_DB_TYPE_MYSQL);
    public static final DbType DB_TYPE_HSQL = new DbType(_DB_TYPE_HSQL);
    public static final DbType DB_TYPE_INFORMIX = new DbType(_DB_TYPE_INFORMIX);
    public static final DbType DB_TYPE_DERBY= new DbType(_DB_TYPE_DERBY);

    protected DbType(final String value) {
        this.value = value;
    }

    public String value () {
      return value;
    }
}
