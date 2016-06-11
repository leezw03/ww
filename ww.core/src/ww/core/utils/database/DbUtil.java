package ww.core.utils.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ww.core.utils.data.DataUtils;

public class DbUtil {
	public static Connection getConnection(String url, String user, String password) throws SQLException {
		Driver driver = new net.sf.log4jdbc.DriverSpy();
		DriverManager.registerDriver(driver);
		Connection connection = DriverManager.getConnection(url, user, password);
		return connection;
	}

	public static void close(Connection conn) throws SQLException {
		if (conn != null) {
			conn.close();
		}
	}

	public static void close(Statement stmt) throws SQLException {
		if (stmt != null) {
			stmt.close();
		}
	}

	public static void closeQuietly(Connection conn) {
		try {
			close(conn);
		} catch (SQLException e) {
			// quiet
		}
	}

	public static void closeQuietly(Statement stmt) {
		try {
			close(stmt);
		} catch (SQLException e) {
			// quiet
		}
	}

	public static Map<String, Object> wrap(ResultSet rs) {
		Map<String, Object> result = new HashMap();
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();

			for (int i = 1; i <= cols; i++) {
				String columnName = rsmd.getColumnLabel(i);
				if (null == columnName || 0 == columnName.length()) {
					columnName = rsmd.getColumnName(i);
				}
				result.put(columnName, rs.getObject(i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) throws SQLException {
		String user = "irms";
		// Connection connection =
		// DbUtil.getConnection("jdbc:derby:E:/apache-tomcat-6.0.20/bin/rmddb;create=true",
		// user, "");
		// Connection connection =
		// DbUtil.getConnection("jdbc:log4jdbc:derby://127.0.0.1:1527/rmddb;create=true",
		// user, null);
		Connection connection = DbUtil.getConnection("jdbc:log4jdbc:oracle:thin:@121.201.251.67:1521:test", user,
				"irms123!@#");

		DatabaseMetaData meta = connection.getMetaData();
		ResultSet tableRs = meta.getTables(null, user.toUpperCase(), null, null);
		List<Map<String, Object>> tableList = new ArrayList<Map<String, Object>>();
		while (tableRs.next()) {
			Map<String, Object> tableMap = DbUtil.wrap(tableRs);
			System.out.println(tableMap.toString());
			String schem = DataUtils.getStringValue(tableMap, "TABLE_SCHEM");
			String table = DataUtils.getStringValue(tableMap, "TABLE_NAME");
			ResultSet crs = meta.getColumns(null, schem, table, null);
			while (crs.next()) {
				Map<String, Object> columnMap = DbUtil.wrap(crs);
				System.out.println("========" + columnMap.toString());
			}
			tableList.add(tableMap);
		}

	}

	public static DbType getDbType(String rawUrl) {
		if (rawUrl == null) {
			return null;
		}
		if (rawUrl.contains(":derby:"))
			return DbType.DB_TYPE_DERBY;
		if (rawUrl.contains(":mysql:"))
			return DbType.DB_TYPE_MYSQL;
		if (rawUrl.contains(":oracle:"))
			return DbType.DB_TYPE_ORACLE;
		if (rawUrl.contains(":informix-sqli:"))
			return DbType.DB_TYPE_INFORMIX;
//		if (rawUrl.startsWith("jdbc:alibaba:oracle:"))
//			return "AliOracle";
//		if (rawUrl.startsWith("jdbc:microsoft:"))
//			return "sqlserver";
//		if (rawUrl.startsWith("jdbc:sybase:Tds:"))
//			return "sybase";
//		if (rawUrl.startsWith("jdbc:jtds:"))
//			return "jtds";
//		if ((rawUrl.startsWith("jdbc:fake:")) || (rawUrl.startsWith("jdbc:mock:")))
//			return "mock";
//		if (rawUrl.startsWith("jdbc:postgresql:"))
//			return "postgresql";
//		if (rawUrl.startsWith("jdbc:hsqldb:"))
//			return "hsql";
//		if (rawUrl.startsWith("jdbc:db2:"))
//			return "db2";
//		if (rawUrl.startsWith("jdbc:sqlite:"))
//			return "sqlite";
//		if (rawUrl.startsWith("jdbc:ingres:"))
//			return "ingres";
//		if (rawUrl.startsWith("jdbc:h2:"))
//			return "h2";
//		if (rawUrl.startsWith("jdbc:mckoi:"))
//			return "mckoi";
//		if (rawUrl.startsWith("jdbc:cloudscape:"))
//			return "cloudscape";
//		if (rawUrl.startsWith("jdbc:informix-sqli:"))
//			return "informix";
//		if (rawUrl.startsWith("jdbc:timesten:"))
//			return "timesten";
//		if (rawUrl.startsWith("jdbc:as400:"))
//			return "as400";
//		if (rawUrl.startsWith("jdbc:sapdb:"))
//			return "sapdb";
//		if (rawUrl.startsWith("jdbc:JSQLConnect:"))
//			return "JSQLConnect";
//		if (rawUrl.startsWith("jdbc:JTurbo:"))
//			return "JTurbo";
//		if (rawUrl.startsWith("jdbc:firebirdsql:"))
//			return "firebirdsql";
//		if (rawUrl.startsWith("jdbc:interbase:"))
//			return "interbase";
//		if (rawUrl.startsWith("jdbc:pointbase:"))
//			return "pointbase";
//		if (rawUrl.startsWith("jdbc:edbc:"))
//			return "edbc";
//		if (rawUrl.startsWith("jdbc:mimer:multi1:"))
//			return "mimer";

		return null;
	}
}
