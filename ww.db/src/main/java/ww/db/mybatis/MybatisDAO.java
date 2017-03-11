package ww.db.mybatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;

import ww.core.mvc.pojo.PageParam;
import ww.db.exception.DataAccessException;
import ww.db.mybatis.pojo.DbRecord;
import ww.db.mybatis.pojo.GenericQueryParam;
import ww.db.mybatis.pojo.Pk;
import ww.db.mybatis.pojo.Pk.PkCol;
import ww.db.mybatis.pojo.Record;
import ww.db.mybatis.pojo.SqlAdapter;
import ww.db.sql.pojo.SqlCondition;
import ww.db.sql.utils.SqlConditionTool;

public class MybatisDAO extends AbsMybatisDAO<Record, Pk> {
	
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
	 * 统计查询数量
	 * @param queryParam
	 * @return
	 */
	public int countByParam(GenericQueryParam queryParam) {
		Map<String, Object> param = this.parseQueryParam(queryParam);
		return super.count(param);
	}
	/**
	 * 查询表
	 * @param queryParam
	 * @return
	 */
	public List<DbRecord> findByParam(GenericQueryParam queryParam) {
		return this.findByParam(null, queryParam);
	}
	/**
	 * 查询表并分页
	 * @param pageParam
	 * @param queryParam
	 * @return
	 */
	public List<DbRecord> findByParam(PageParam pageParam, GenericQueryParam queryParam) {
		Map<String, Object> param = this.parseQueryParam(queryParam);
		if(pageParam != null) {
			RowBounds bounds = new RowBounds(pageParam.getStart(), pageParam.getLimit());
			return this.getSqlSession().selectList(getSqlMap()+".findEntity", param, bounds);
		} else {
			return this.getSqlSession().selectList(getSqlMap()+".findEntity", param);
		}
	}
	
	private Map<String, Object> parseQueryParam(GenericQueryParam queryParam) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("_table", queryParam.getTable());
		param.put("_columnList", queryParam.getColumnList());
		param.put("_sort", queryParam.getSort());
		List<String> _conditionList = new ArrayList<String>();
		List<SqlCondition> conditionList = queryParam.getConditionList();
		for(SqlCondition condition : conditionList) {
			String text = SqlConditionTool.getInstance(this.getDbType()).getConditionText(condition);
			if(StringUtils.isNotBlank(text)) {
				_conditionList.add(text);
			}
		}
		param.put("_conditionList", _conditionList);
		if(queryParam.getVarMap() != null) {
			param.putAll(queryParam.getVarMap());
		}
		return param;
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
