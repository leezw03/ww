package ww.core.mybatis;

import java.io.Serializable;
import java.util.List;

import ww.core.mybatis.pojo.Record;

public class MybatisDAO extends GenericMybatisDAO<Record, Serializable> {
	
	private static final String SQL_MAP = "_Mybatis_";
	
	public List<Record> findBySql(String sql) {
		return this.getSqlSession().selectList(SQL_MAP+".findBySql");
	}
}
