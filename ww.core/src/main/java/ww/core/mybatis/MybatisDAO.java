package ww.core.mybatis;

import java.io.Serializable;
import java.util.List;

import ww.core.mybatis.pojo.DbRecord;
import ww.core.mybatis.pojo.Record;
import ww.core.mybatis.pojo.SqlAdapter;

public class MybatisDAO extends GenericMybatisDAO<Record, Serializable> {
	
	private static final String SQL_MAP = "_Mybatis_";
	
	public List<DbRecord> findBySql(String sql) {
		SqlAdapter sqlAdapter = new SqlAdapter(sql);
		return this.getSqlSession().selectList(SQL_MAP+".findBySql", sqlAdapter);
	}
	
	public int updateBySql(String sql) {
		SqlAdapter sqlAdapter = new SqlAdapter(sql);
		return this.getSqlSession().update(SQL_MAP+".updateBySql", sqlAdapter);
	}
	
	public int deleteBySql(String sql) {
		SqlAdapter sqlAdapter = new SqlAdapter(sql);
		return this.getSqlSession().update(SQL_MAP+".deleteBySql", sqlAdapter);
	}
}
