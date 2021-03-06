package ww.db.mybatis;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import ww.core.mvc.pojo.PageParam;
import ww.db.dao.IGenericDAO;
import ww.db.em.DbType;
import ww.db.exception.DataAccessException;

public abstract class AbsMybatisDAO<T, ID extends Serializable> extends SqlSessionDaoSupport implements IGenericDAO<T, ID> {

	protected abstract String getSqlMap();
	
	public void insert(T entity) {
		this.getSqlSession().insert(getSqlMap()+".insertEntity", entity);
	}
    
    public int update(T entity) {
    	return this.getSqlSession().update(getSqlMap()+".updateEntity", entity);
    }
    
    public int delete(ID primaryKey) {
    	return this.getSqlSession().delete(getSqlMap()+".deleteEntity", primaryKey);
    }
    
    public T get(ID id) {
    	return this.getSqlSession().selectOne(getSqlMap()+".getEntity", id);
    }
    
    public T load(ID id) throws DataAccessException {
    	T entity = this.get(id);
    	if(entity == null) {
    		throw new DataAccessException(String.format("未找到数据（ID：%s）！", id.toString()));
    	}
    	return entity;
    }
    
    public void batchInsert(final List<T> list) {
    	if(!list.isEmpty()) {
	    	SqlSessionTemplate sqlSessionTemplate = null;
	    	SqlSession sqlSession = this.getSqlSession();
	    	if(sqlSession instanceof SqlSessionTemplate) {
	    		sqlSessionTemplate = (SqlSessionTemplate) sqlSession;
	    	}
	    	if(sqlSessionTemplate != null) {
	    		SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
	    		for(T entity : list) {
	    			sqlSessionTemplate.insert(getSqlMap()+".insertEntity", entity);
	    		}
	    		session.commit();
	    	}
    	}
    }
    
    public void batchUpdate(final List<T> list) {
    	if(!list.isEmpty()) {
    		this.getSqlSession().update(getSqlMap()+".batchUpdateEntity", list);
    	}
    }
    
    public void batchDelete(final List<ID> list) {
    	if(!list.isEmpty()) {
    		this.getSqlSession().delete(getSqlMap()+".batchDeleteEntity", list);
    	}
    }
    
    public int count(Object queryParam) {
    	return this.getSqlSession().selectOne(getSqlMap()+".countEntity", queryParam);
    }
    
    public List<T> find(Object queryParam) {
    	return this.getSqlSession().selectList(getSqlMap()+".findEntity", queryParam);
    }
    
    public List<T> find(PageParam pageParam, Object queryParam) {
    	if(pageParam == null) {
    		throw new IllegalArgumentException("分页条件不能为空！");
    	}
    	RowBounds bounds = new RowBounds(pageParam.getStart(), pageParam.getLimit());
    	return this.getSqlSession().selectList(getSqlMap()+".findEntity", queryParam, bounds);
    }
    
    public DbType getDbType() {
    	String databaseId = this.getSqlSession().getConfiguration().getDatabaseId();
    	return DbType.get(databaseId);
    }
}
