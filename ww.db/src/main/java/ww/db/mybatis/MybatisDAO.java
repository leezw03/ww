package ww.db.mybatis;

import java.util.List;

import ww.db.exception.DataAccessException;
import ww.db.mybatis.pojo.DbRecord;
import ww.db.mybatis.pojo.Pk;
import ww.db.mybatis.pojo.Pk.PkCol;
import ww.db.mybatis.pojo.Record;
import ww.db.mybatis.pojo.SqlAdapter;

public class MybatisDAO extends GenericMybatisDAO<Record, Pk> {
	
	private static final String SQL_MAP = "_Mybatis_";
	
	@Override
	protected String getSqlMap() {
		return SQL_MAP;
	}
	
	@Override
	public void insert(Record r) {
		if(r.getPk() != null) {
			for(PkCol pkCol : r.getPk().getColList()) {
				r.addData(pkCol.getName(), pkCol.getValue());
			}
		}
		super.insert(r);
	}
	
	@Override
	public int update(Record r) {
		if(r.getPk() == null) {
			throw new DataAccessException("主键不能为空！");
		}
		return super.update(r);
	}
	
	@Override
	public void batchInsert(List<Record> list) {
		for(Record r : list) {
			if(r.getPk() != null) {
				for(PkCol pkCol : r.getPk().getColList()) {
					r.addData(pkCol.getName(), pkCol.getValue());
				}
			}
		}
		super.batchInsert(list);
	}
	
	@Override
	public Record get(Pk pk) {
		Record r = new Record(pk);
		DbRecord dbRecord = this.getSqlSession().selectOne(getSqlMap()+".getEntity", r);
		if(dbRecord != null) {
			r.setDatas(dbRecord);
		} else {
			r = null;
		}
		return r;
	}

	/**
	 * 根据sql查询
	 * @param sql
	 * @return
	 */
	public List<DbRecord> findBySql(String sql) {
		SqlAdapter sqlAdapter = new SqlAdapter(sql);
		return this.getSqlSession().selectList(this.getSqlMap()+".findBySql", sqlAdapter);
	}
	/**
	 * 根据sql更新
	 * @param sql
	 * @return
	 */
	public int updateBySql(String sql) {
		SqlAdapter sqlAdapter = new SqlAdapter(sql);
		return this.getSqlSession().update(this.getSqlMap()+".updateBySql", sqlAdapter);
	}
	/**
	 * 根据sql删除
	 * @param sql
	 * @return
	 */
	public int deleteBySql(String sql) {
		SqlAdapter sqlAdapter = new SqlAdapter(sql);
		return this.getSqlSession().delete(this.getSqlMap()+".deleteBySql", sqlAdapter);
	}
}
