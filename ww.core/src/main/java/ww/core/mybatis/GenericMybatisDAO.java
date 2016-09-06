package ww.core.mybatis;

import java.io.Serializable;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.dao.DataAccessException;

import ww.core.mvc.dao.IGenericDAO;
import ww.core.mvc.pojo.PageParam;
import ww.core.mvc.pojo.QueryParam;

public abstract class GenericMybatisDAO<T, ID extends Serializable> extends SqlSessionDaoSupport implements IGenericDAO<T, ID> {
	
	private final static String SQL_MAP = "_Generic_";

	public void insert(T entity) {
		
	}
    
    public int update(T entity) {
    	return 0;
    }
    
    public int delete(ID primaryKey) {
    	return 0;
    }
    
    public T get(ID id) {
    	return null;
    }
    
    public T load(ID id) throws DataAccessException {
    	return null;
    }
    
    public void batchInsert(final List<T> list) {
    	
    }
    
    public void batchUpdate(final List<T> list) {
    	
    }
    
    public void batchDelete(final List<ID> list) {
    	
    }
    
    public int count(QueryParam queryParam) {
    	return 0;
    }
    
    public List<T> find(QueryParam queryParam) {
    	return null;
    }
    
    public List<T> find(PageParam pageParam, QueryParam queryParam) {
    	return null;
    }
}