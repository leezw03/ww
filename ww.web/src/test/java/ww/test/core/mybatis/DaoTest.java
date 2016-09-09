package ww.test.core.mybatis;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;

import ww.core.mybatis.MybatisDAO;
import ww.core.mybatis.pojo.DbRecord;
import ww.core.mybatis.pojo.Pk;
import ww.core.mybatis.pojo.Record;
import ww.core.spring.BeanUtils;

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
	@Test
	public void testUpdate() {
		MybatisDAO mybatisDAO = (MybatisDAO)BeanUtils.get("MybatisDAO");
		Pk pk = new Pk("CUID", "T_SYS_LOG-8aac7fc651f3fd460151f427faaf0009");
		Record r = new Record("T_SYS_LOG");
		r.addData("RES_NAME", "123");
		r.addData("CREATE_TIME", new Date());
		mybatisDAO.update(pk, r);
	}
}
