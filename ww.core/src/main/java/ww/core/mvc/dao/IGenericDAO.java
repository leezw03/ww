package ww.core.mvc.dao;
import java.io.Serializable;
import java.util.List;

import org.springframework.dao.DataAccessException;

import ww.core.mvc.pojo.PageParam;
import ww.core.mvc.pojo.QueryParam;

/**
 * IGenericDao DAO层泛型接口，定义基本的DAO功能
 * @author lizhiwei
 * 
 * @param <T> 实体类
 * @param <ID> 主键类，必须实现Serializable接口
 * 
 */
public abstract interface IGenericDAO<T, ID extends Serializable> {
    /**
     * 插入一个实体
     * @param entity 实体对象
     */
    public abstract void insert(T entity);
    
    /**
     * 修改一个实体对象
     * @param entity 实体对象
     * @return 修改的对象个数，正常情况=1
     */
    public abstract int update(ID primaryKey, T entity);
    
    /**
     * 按主键删除记录
     * @param id 主键对象
     * @return 删除的对象个数，正常情况=1
     */
    public abstract int delete(ID primaryKey);

    /**
     * 按主键取记录
     * @param id 主键值
     * @return 记录实体对象，如果没有符合主键条件的记录，则返回null
     */
    public abstract T get(ID id);

    /**
     * 按主键取记录
     * @param id 主键值
     * @return 记录实体对象，如果没有符合主键条件的记录，则 throw DataAccessException
     */
    public abstract T load(ID id) throws DataAccessException;

    /**
     * 批量插入
     * @param list
     */
    public abstract void batchInsert(final List<T> list);
    
    /**
     * 批量修改
     * @param list
     */
    public abstract void batchUpdate(final List<T> list);
    
    /**
     * 批量删除
     * @param list
     */
    public abstract void batchDelete(final List<ID> list);
    
    /**
     * 按条件获取数据总数
     * @param queryParam 查询条件
     * @return 查询数量
     */
    public abstract int count(QueryParam queryParam);
    
    /**
     * 查询数据
     * @param queryParam 查询条件
     * @return 全部记录实体对象的List
     */
    public abstract List<T> find(QueryParam queryParam);
    /**
     * 分页查询数据
     * @param pageParam 分页参数
     * @param queryParam 查询条件
     * @return 查询的实体集合。
     */
    public abstract List<T> find(PageParam pageParam, QueryParam queryParam);
}
