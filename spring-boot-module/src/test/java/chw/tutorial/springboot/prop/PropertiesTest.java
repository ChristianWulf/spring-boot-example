package chw.tutorial.springboot.prop;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
// @SpringBootTest // works
@DataJpaTest // works
// @SpringBootTest(webEnvironment=WebEnvironment.NONE) // does not work
// @ContextConfiguration // does not work
public class PropertiesTest {

	@Autowired
	private Environment environment;

	@Test
	public void main_property_is_true() throws Exception {
		String propertyValue = environment.getProperty("chw.main");
		// ensure that application.properties from sec/main/resource is not loaded by
		// Spring Boot
		assertThat(propertyValue, is(nullValue()));
	}

	@Test
	public void test_property_is_true() throws Exception {
		String propertyValue = environment.getProperty("chw.test");
		assertThat(propertyValue, is(Boolean.toString(true)));
	}
}
