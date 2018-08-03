package chw.tutorial.springboot.employee;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.ldap.AutoConfigureDataLdap;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
@AutoConfigureDataLdap
public class EmployeeRestControllerTest {

	// https://github.com/rwinch/spring-security-test-blog/blob/master/src/test/java/org/springframework/security/test/web/servlet/showcase/login/AuthenticationTests.java

	@Autowired
	private MockMvc mvc;

	@MockBean
	private EmployeeService service;

	@Test
	@WithMockUser	// @WithMockUser annotation ensures a login with valid credentials
	public void givenEmployees_whenGetEmployees_thenReturnJsonArray() throws Exception {
		Employee alex = new Employee("alex");

		List<Employee> allEmployees = Arrays.asList(alex);

		given(service.getAllEmployees()).willReturn(allEmployees);

		// @formatter:off
		mvc.perform(get("/employees2")
//			.with(httpBasic("bob","bobspassword"))
//			.with(httpBasic("user","password"))
//			.with(httpBasic("wulfchr","password4"))
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(1)))
			.andExpect(jsonPath("$[0].name", is(alex.getName())));
		// @formatter:on
	}

}