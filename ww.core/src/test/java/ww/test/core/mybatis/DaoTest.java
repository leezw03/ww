package ww.test.core.mybatis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = { "classpath*:ww/config/spring/spring-*.xml",
		"classpath*:ww/config/webonly/spring/spring-*.xml", "classpath*config/spring/spring-*.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class DaoTest {

	@Test
	public void TestIndex(){
		
	}
}
