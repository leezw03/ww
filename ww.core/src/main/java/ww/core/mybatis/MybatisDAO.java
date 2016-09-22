package ww.core.mybatis;

import java.util.List;

import ww.core.mybatis.pojo.DbRecord;
import ww.core.mybatis.pojo.Pk;
import ww.core.mybatis.pojo.Record;
import ww.core.mybatis.pojo.SqlAdapter;

public class MybatisDAO extends GenericMybatisDAO<Record, Pk> {
	
	private static final String SQL_MAP = "_Mybatis_";
	
	@Override
	protected String getSqlMap() {
		return SQL_MAP;
	}
	
	@Override
	public int update(Pk pk, Record r) {
		r.setPk(pk);
		return this.getSqlSession().update(getSqlMap()+".updateRecord", r);
	}
	
	@Override
	public void insert(Record r) {
		this.getSqlSession().insert(getSqlMap()+".insertRecord", r);
	}

	public List<DbRecord> findBySql(String sql) {
		SqlAdapter sqlAdapter = new SqlAdapter(sql);
		return this.getSqlSession().selectList(this.getSqlMap()+".findBySql", sqlAdapter);
	}
	
	public int updateBySql(String sql) {
		SqlAdapter sqlAdapter = new SqlAdapter(sql);
		return this.getSqlSession().update(this.getSqlMap()+".updateBySql", sqlAdapter);
	}
	
	public int deleteBySql(String sql) {
		SqlAdapter sqlAdapter = new SqlAdapter(sql);
		return this.getSqlSession().delete(this.getSqlMap()+".deleteBySql", sqlAdapter);
	}
}
