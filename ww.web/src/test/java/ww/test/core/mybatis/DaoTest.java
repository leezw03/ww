package ww.test.core.mybatis;

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
import ww.core.spring.BeanUtils;

@ContextConfiguration(locations = { "classpath*:ww/config/spring/spring-*.xml",
		"classpath*:ww/config/webonly/spring/spring-*.xml", "classpath*:config/spring/spring-*.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class DaoTest extends AbstractJUnit4SpringContextTests {

	@Test
	public void TestIndex(){
		MybatisDAO mybatisDAO = (MybatisDAO)BeanUtils.get("MybatisDAO");
		List<DbRecord> list = mybatisDAO.findBySql("SELECT 'ABS' AS NAME FROM DUAL");
		System.out.println(JSONArray.toJSON(list));
	}
}
