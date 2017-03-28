package ww.cmp.tpl.bo;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import ww.cmp.tpl.TplLoader;
import ww.cmp.tpl.handler.sql.SqlTpl;
import ww.cmp.tpl.handler.sql.SqlTpl.DynamicItem;
import ww.core.exception.BusiException;
import ww.db.mybatis.MybatisDAO;
import ww.db.mybatis.pojo.SqlQueryParam;

public class SqlTplBO {
	
	private MybatisDAO mybatisDAO;

	public void setMybatisDAO(MybatisDAO mybatisDAO) {
		this.mybatisDAO = mybatisDAO;
	}

	public int count(String tplName, SqlQueryParam queryParam) {
		SqlTpl tpl = (SqlTpl) TplLoader.getInstance().getTpl(SqlTpl.TYPE, tplName);
		if(tpl == null) {
			throw new BusiException(String.format("未找到tplName=%s的配置！", tplName));
		}
		String sqlText = this.getSql(tpl, queryParam);
		return mybatisDAO.countSql(sqlText);
	}
	
	private String getSql(SqlTpl tpl, SqlQueryParam queryParam) {
		String baseSql = tpl.getSqlText();
		List<DynamicItem> dynamicItems = tpl.getDynamicItems();
		if(dynamicItems != null) {
			for(DynamicItem dynamicItem : dynamicItems) {
				String alias = dynamicItem.getAlias();
				String name = dynamicItem.getName();
				String itemText = dynamicItem.getItemText();
				if(StringUtils.isNotBlank(alias)) {
					
				}
			}
		}
		return null;
	}
}
