package ww.core.ibatis.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.engine.execution.SqlExecutor;
import com.ibatis.sqlmap.engine.impl.SqlMapClientImpl;
import com.ibatis.sqlmap.engine.impl.SqlMapExecutorDelegate;

import ww.core.utils.lang.ReflectUtil;

/**
 * 用于生成SqlMapClient,但增加设置sqlExecutor属性,以便用于扩展ibatis使用数据库物理分页
 * 
 * @author badqiu
 * 
 */
@SuppressWarnings("deprecation")
public class SqlMapClientFactoryBean extends
		org.springframework.orm.ibatis.SqlMapClientFactoryBean {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	protected SqlExecutor sqlExecutor;

	public SqlExecutor getSqlExecutor() {
		return sqlExecutor;
	}

	public void setSqlExecutor(SqlExecutor sqlExecutor) {
		this.sqlExecutor = sqlExecutor;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		super.afterPropertiesSet();
		this.invokeSqlExecutor();
	}

	/**
	 * 插入自定义的LimitSqlExecutor
	 */
	protected void invokeSqlExecutor(){
		SqlMapClient c = (SqlMapClient) getObject();
		if (sqlExecutor != null && c instanceof SqlMapClientImpl) {
			SqlMapClientImpl client = (SqlMapClientImpl) c;
			SqlMapExecutorDelegate delegate = client.getDelegate();
			try {
				ReflectUtil.setFieldValue(delegate, "sqlExecutor",
						SqlExecutor.class, sqlExecutor);
				logger.debug("[iBATIS] success set ibatis SqlMapClient.sqlExecutor = "
								+ sqlExecutor.getClass().getName());
			} catch (Exception e) {
				logger.error("[iBATIS] error,cannot set ibatis SqlMapClient.sqlExecutor = "
								+ sqlExecutor.getClass().getName()
								+ " cause:"
								+ e);
			}
		}
	}
}
