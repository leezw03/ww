package ww.db.mybatis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import ww.core.mvc.pojo.PageParam;
import ww.core.spring.BeanUtils;
import ww.db.mybatis.pojo.DbRecord;
import ww.db.mybatis.pojo.GenericQueryParam;
import ww.db.mybatis.pojo.Pk;
import ww.db.mybatis.pojo.Record;
import ww.db.sql.em.SqlExpression;
import ww.db.sql.em.SqlValueType;
import ww.db.sql.pojo.SqlCondition;
import ww.test.AbstractTests;

public class MybatisDAOTest extends AbstractTests {

	public void testFindBySql(){
		MybatisDAO mybatisDAO = BeanUtils.get("MybatisDAO", MybatisDAO.class);
		List<DbRecord> list = mybatisDAO.findBySql("SELECT 'ABS' AS NAME FROM DUAL");
		System.out.println(JSONArray.toJSON(list));
	}
	
	public void testUpdate() {
		MybatisDAO mybatisDAO = BeanUtils.get("MybatisDAO", MybatisDAO.class);
		Pk pk = new Pk("T_SYS_LOG", "CUID", "T_SYS_LOG-8aac7fc651f3fd460151f427faaf0009");
		Record r = new Record(pk);
		r.addData("RES_NAME", "123");
		r.addData("CREATE_TIME", new Date());
		r.addSqlData("ACTION_TYPE", "SELECT 'ABC' FROM DUAL");
		mybatisDAO.update(r);
	}
	
	public void testUpdateBatch() {
		MybatisDAO mybatisDAO = BeanUtils.get("MybatisDAO", MybatisDAO.class);
		Pk pk1 = new Pk("T_SYS_LOG", "CUID", "T_SYS_LOG-8aac7fc651f3fd460151f427faaf0009");
		Record r1 = new Record(pk1);
		r1.addData("RES_NAME", "123");
		r1.addData("CREATE_TIME", new Date());
		r1.addSqlData("ACTION_TYPE", "SELECT 'ABC' FROM DUAL");
		
		Pk pk2 = new Pk("T_SYS_LOG", "CUID", "T_SYS_LOG-8aac7fc651f3fd460151f427faaf0010");
		Record r2 = new Record(pk2);
		r2.addData("RES_NAME", "123");
		r2.addData("CREATE_TIME", new Date());
		r2.addSqlData("ACTION_TYPE", "SELECT 'ABC' FROM DUAL");
		
		List<Record> rList = new ArrayList<Record>();
		rList.add(r1);
		rList.add(r2);
		mybatisDAO.batchUpdate(rList);
	}
	
	public void testInsert() {
		MybatisDAO mybatisDAO = BeanUtils.get("MybatisDAO", MybatisDAO.class);
		Record r = new Record("T_SYS_LOG");
		r.addData("CUID", "T_SYS_LOG-8aac7fc651f3fd460151f427faaf0010");
		r.addData("RES_NAME", "123");
		r.addData("CREATE_TIME", new Date());
		r.addSqlData("ACTION_TYPE", "SELECT 'ABC' FROM DUAL");
		mybatisDAO.insert(r);
	}
	
	public void testInsertBatch() {
		MybatisDAO mybatisDAO = BeanUtils.get("MybatisDAO", MybatisDAO.class);
		Pk pk1 = new Pk("T_SYS_LOG", "CUID", "T_SYS_LOG-8aac7fc651f3fd460151f427faaf0013");
		Record r1 = new Record(pk1);
		r1.addData("RES_NAME", "123");
		r1.addData("CREATE_TIME", new Date());
		r1.addSqlData("ACTION_TYPE", "SELECT 'ABC' FROM DUAL");
		
		Pk pk2 = new Pk("T_SYS_LOG", "CUID", "T_SYS_LOG-8aac7fc651f3fd460151f427faaf0014");
		Record r2 = new Record(pk2);
		r2.addData("RES_NAME", "123");
		r2.addData("CREATE_TIME", new Date());
		r2.addSqlData("ACTION_TYPE", "SELECT 'ABC' FROM DUAL");
		
		List<Record> rList = new ArrayList<Record>();
		rList.add(r1);
		rList.add(r2);
		mybatisDAO.batchInsert(rList);
	}
	
	public void testDelete() {
		MybatisDAO mybatisDAO = BeanUtils.get("MybatisDAO", MybatisDAO.class);
		Pk pk = new Pk("T_SYS_LOG", "CUID", "T_SYS_LOG-8aac7fc651f3fd460151f427faaf0010");
		mybatisDAO.delete(pk);
	}
	
	public void testGet() {
		MybatisDAO mybatisDAO = BeanUtils.get("MybatisDAO", MybatisDAO.class);
		Pk pk = new Pk("T_SYS_LOG", "CUID", "T_SYS_LOG-8aac7fc651f3fd460151f427faaf0009");
		Record r = mybatisDAO.get(pk);
	}
	
	
	public void testFindByParam() {
		MybatisDAO mybatisDAO = BeanUtils.get("MybatisDAO", MybatisDAO.class);
		GenericQueryParam queryParam = new GenericQueryParam("WF_OPERATOR");
		//queryParam.addCondition(new SqlCondition("HOST", "localhost"));
		//queryParam.addCondition(new SqlCondition("HOST", "local*", SqlValueType.string, SqlExpression.like));
		Date now = new Date();
		Date fromDate = DateUtils.addDays(now, -1);
		Date toDate = DateUtils.addDays(now, 1);
		//queryParam.addCondition(new SqlCondition("PASSWORD_LAST_CHANGED", new Date[]{fromDate, toDate}, SqlValueType.date, SqlExpression.between));
		//queryParam.addCondition(new SqlCondition("PASSWORD_LAST_CHANGED", fromDate, SqlValueType.date, SqlExpression.ge));
		//queryParam.addCondition(new SqlCondition("MAX_UPDATES", 10, SqlValueType.number, SqlExpression.le));
		//queryParam.addCondition(new SqlCondition("_append1", "USER = 'root'", SqlValueType.string, SqlExpression.append));
		//queryParam.addCondition(new SqlCondition("_exists1", "SELECT SERVER_NAME FROM SERVERS R WHERE R.SERVER_NAME = USER", SqlValueType.string, SqlExpression.exists));
		//queryParam.addCondition(new SqlCondition("SELECT_PRIV", new String[]{"Y", "N"}, SqlValueType.string, SqlExpression.in));
		queryParam.addCondition(new SqlCondition("STATUS", new String[]{"1", "0"}, SqlValueType.number, SqlExpression.in));
		List<DbRecord> list = mybatisDAO.findByParam(queryParam);
		System.out.println(JSON.toJSONString(list));
	}
	
	@Test
	public void testFindByPage() {
		MybatisDAO mybatisDAO = BeanUtils.get("MybatisDAO", MybatisDAO.class);
		GenericQueryParam queryParam = new GenericQueryParam("WF_OPERATOR");
		queryParam.addCondition(new SqlCondition("USER_ID", "*abc*", SqlValueType.string, SqlExpression.like));
		PageParam page = new PageParam(0, 10);
		List<DbRecord> list = mybatisDAO.findByParam(page, queryParam);
		System.out.println(JSON.toJSONString(list));
	}
}
