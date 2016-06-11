package ww.core.ibatis.utils;

import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.alibaba.druid.proxy.jdbc.ClobProxyImpl;
import com.ibatis.sqlmap.engine.type.ObjectTypeHandler;

import oracle.sql.BLOB;
import oracle.sql.CLOB;
import oracle.sql.DATE;
import oracle.sql.TIMESTAMP;
import oracle.sql.TIMESTAMPLTZ;
import oracle.sql.TIMESTAMPTZ;

public class OracleObjectTypeHandler extends ObjectTypeHandler {

	public void setParameter(PreparedStatement ps, int i, Object parameter,
			String jdbcType) throws SQLException {
		super.setParameter(ps, i, parameter, jdbcType);
	}

	public Object valueOf(String s) {
		return super.valueOf(s);
	}

	public Object getResult(ResultSet rs, String columnName)
			throws SQLException {
		Object object = rs.getObject(columnName);
		if (rs.wasNull()) {
			return null;
		} else {
			return fix(object, rs, columnName);
		}
	}

	protected Object fix(Object obj) {
		try {
			if (obj instanceof TIMESTAMP) {
				return new Date(((TIMESTAMP) obj).dateValue().getTime());
			} else if (obj instanceof DATE) {
				return new Date(((DATE) obj).dateValue().getTime());
			} else if (obj instanceof TIMESTAMPLTZ) {
				return new Date(((TIMESTAMPLTZ) obj).dateValue().getTime());
			} else if (obj instanceof TIMESTAMPTZ) {
				return new Date(((TIMESTAMPTZ) obj).dateValue().getTime());
			} else if (obj instanceof CLOB) {
				return oracleClob2Str((CLOB) obj);
			}else if(obj instanceof ClobProxyImpl){
				return oracleClob2Str(((ClobProxyImpl) obj).getRawClob());
			} else if (obj instanceof BLOB) {
				Blob blob = (BLOB) obj;
				int size = (int) blob.length();
				return blob.getBytes(1, size);

			} else {
				return obj;
			}
		} catch (Exception e) {
			return obj;
		}
	}

	private String oracleClob2Str(Clob clob) {
		try {
			return (clob != null ? clob.getSubString(1, (int) clob.length())
					: null);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	protected Object fix(Object obj, ResultSet rs, String columnName) {
		try {
			if (obj instanceof DATE) {
				obj = rs.getTimestamp(columnName);
				return new Date(((TIMESTAMP) obj).dateValue().getTime());
			} else if (obj instanceof Date) {
				obj = rs.getTimestamp(columnName);
				return new Date(((TIMESTAMP) obj).dateValue().getTime());
			} else {
				return fix(obj);
			}
		} catch (Exception e) {
			return obj;
		}
	}

	protected Object fix(Object obj, ResultSet rs, int columnIndex) {
		try {
			if (obj instanceof DATE) {
				obj = rs.getTimestamp(columnIndex);
				return new Date(((TIMESTAMP) obj).dateValue().getTime());
			} else if (obj instanceof Date) {
				obj = rs.getTimestamp(columnIndex);
				return new Date(((TIMESTAMP) obj).dateValue().getTime());
			} else {
				return fix(obj);
			}
		} catch (Exception e) {
			return obj;
		}
	}

	protected Object fix(Object obj, CallableStatement cs, int columnIndex) {
		try {
			if (obj instanceof DATE) {
				obj = cs.getTimestamp(columnIndex);
				return new Date(((TIMESTAMP) obj).dateValue().getTime());
			} else if (obj instanceof Date) {
				obj = cs.getTimestamp(columnIndex);
				return new Date(((TIMESTAMP) obj).dateValue().getTime());
			} else {
				return fix(obj);
			}
		} catch (Exception e) {
			return obj;
		}
	}

	public Object getResult(ResultSet rs, int columnIndex) throws SQLException {
		Object object = rs.getObject(columnIndex);
		if (rs.wasNull()) {
			return null;
		} else {
			return fix(object, rs, columnIndex);
		}

	}

	public Object getResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		Object object = cs.getObject(columnIndex);
		if (cs.wasNull()) {
			return null;
		} else {
			return fix(object, cs, columnIndex);
		}
	}

}
