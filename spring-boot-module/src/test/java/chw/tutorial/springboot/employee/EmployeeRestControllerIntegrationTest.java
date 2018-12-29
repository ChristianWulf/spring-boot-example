package chw.tutorial.springboot.employee;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
// @SpringBootTest(classes = Application.class, webEnvironment =
// SpringBootTest.WebEnvironment.MOCK)
@WebMvcTest(value=EmployeeController.class)
@AutoConfigureMockMvc
//@EnableJpaRepositories
// @TestPropertySource(locations =
// "classpath:application-integrationtest.properties")
public class EmployeeRestControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	EmployeeService employeeService;

//	@Autowired
//	private EmployeeRepository repository;

	@Test
	public void givenEmployees_whenGetEmployees_thenStatus200() throws Exception {
		// createTestEmployee("bob");

		// @formatter:off
//		mvc.perform(get("/api/employees").contentType(MediaType.APPLICATION_JSON))
//			.andExpect(status().isOk())
//			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//			.andExpect(jsonPath("$[0].name", is("bob")));
		// @formatter:on
	}
}