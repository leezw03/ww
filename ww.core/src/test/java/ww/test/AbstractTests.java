package ww.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = {
		"classpath*:config/spring/spring-*.xml",
		"classpath*:config/webonly/spring/spring-*.xml",
		"classpath*:ww/config/spring/spring-*.xml",
		"classpath*:ww/config/webonly/spring/spring-*.xml",
		"classpath*:META-INF/ww/config/spring/spring-*.xml",
		"classpath*:META-INF/ww/config/webonly/spring/spring-*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class AbstractTests extends AbstractJUnit4SpringContextTests {

}
