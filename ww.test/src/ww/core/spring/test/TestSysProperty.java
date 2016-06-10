package ww.core.spring.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ww.core.spring.SysProperty;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/spring/spring-*.xml")
public class TestSysProperty extends AbstractJUnit4SpringContextTests {

	@Test
    public void getValue() {
		String pathTemp = SysProperty.getValue("path.temp");
		System.out.println("path.temp="+pathTemp);
	}
}
