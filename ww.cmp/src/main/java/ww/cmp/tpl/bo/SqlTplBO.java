package ww.cmp.tpl.bo;

import ww.cmp.tpl.TplLoader;
import ww.cmp.tpl.handler.sql.SqlTpl;
import ww.core.exception.BusiException;
import ww.db.mybatis.MybatisDAO;
import ww.db.mybatis.pojo.GenericQueryParam;

public class SqlTplBO {
	
	private MybatisDAO mybatisDAO;

	public void setMybatisDAO(MybatisDAO mybatisDAO) {
		this.mybatisDAO = mybatisDAO;
	}

	public int countByParam(String tplName, GenericQueryParam queryParam) {
		SqlTpl tpl = (SqlTpl) TplLoader.getInstance().getTpl(SqlTpl.TYPE, tplName);
		if(tpl == null) {
			throw new BusiException(String.format("未找到tplName=%s的配置！", tplName));
		}
		String sqlText = this.getSql(tpl, queryParam);
		return mybatisDAO.countBySql(sqlText);
	}
	
	private String getSql(SqlTpl tpl, GenericQueryParam queryParam) {
		String baseSql = tpl.getSqlText();
		
		return null;
	}
}
