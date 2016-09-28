package ww.test.core.mybatis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;

import ww.core.spring.BeanUtils;
import ww.db.mybatis.MybatisDAO;
import ww.db.mybatis.pojo.DbRecord;
import ww.db.mybatis.pojo.Pk;
import ww.db.mybatis.pojo.Record;

@ContextConfiguration(locations = { "classpath*:ww/config/spring/spring-*.xml",
		"classpath*:ww/config/webonly/spring/spring-*.xml", "classpath*:config/spring/spring-*.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class DaoTest extends AbstractJUnit4SpringContextTests {

	public void testFindBySql(){
		MybatisDAO mybatisDAO = (MybatisDAO)BeanUtils.get("MybatisDAO");
		List<DbRecord> list = mybatisDAO.findBySql("SELECT 'ABS' AS NAME FROM DUAL");
		System.out.println(JSONArray.toJSON(list));
	}
	
	public void testUpdate() {
		MybatisDAO mybatisDAO = (MybatisDAO)BeanUtils.get("MybatisDAO");
		Pk pk = new Pk("T_SYS_LOG", "CUID", "T_SYS_LOG-8aac7fc651f3fd460151f427faaf0009");
		Record r = new Record(pk);
		r.addData("RES_NAME", "123");
		r.addData("CREATE_TIME", new Date());
		r.addSqlData("ACTION_TYPE", "SELECT 'ABC' FROM DUAL");
		mybatisDAO.update(r);
	}
	
	public void testUpdateBatch() {
		MybatisDAO mybatisDAO = (MybatisDAO)BeanUtils.get("MybatisDAO");
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
		MybatisDAO mybatisDAO = (MybatisDAO)BeanUtils.get("MybatisDAO");
		Record r = new Record("T_SYS_LOG");
		r.addData("CUID", "T_SYS_LOG-8aac7fc651f3fd460151f427faaf0010");
		r.addData("RES_NAME", "123");
		r.addData("CREATE_TIME", new Date());
		r.addSqlData("ACTION_TYPE", "SELECT 'ABC' FROM DUAL");
		mybatisDAO.insert(r);
	}
	@Test
	public void testInsertBatch() {
		MybatisDAO mybatisDAO = (MybatisDAO)BeanUtils.get("MybatisDAO");
		Pk pk1 = new Pk("T_SYS_LOG", "CUID", "T_SYS_LOG-8aac7fc651f3fd460151f427faaf0011");
		Record r1 = new Record(pk1);
		r1.addData("RES_NAME", "123");
		r1.addData("CREATE_TIME", new Date());
		r1.addSqlData("ACTION_TYPE", "SELECT 'ABC' FROM DUAL");
		
		Pk pk2 = new Pk("T_SYS_LOG", "CUID", "T_SYS_LOG-8aac7fc651f3fd460151f427faaf0012");
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
		MybatisDAO mybatisDAO = (MybatisDAO)BeanUtils.get("MybatisDAO");
		Pk pk = new Pk("T_SYS_LOG", "CUID", "T_SYS_LOG-8aac7fc651f3fd460151f427faaf0010");
		mybatisDAO.delete(pk);
	}
	
	public void testGet() {
		MybatisDAO mybatisDAO = (MybatisDAO)BeanUtils.get("MybatisDAO");
		Pk pk = new Pk("T_SYS_LOG", "CUID", "T_SYS_LOG-8aac7fc651f3fd460151f427faaf0009");
		Record r = mybatisDAO.get(pk);
	}
}
