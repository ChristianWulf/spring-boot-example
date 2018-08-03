package chw.tutorial.springboot.employee;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
// @DataJpaTest // configuring H2, an in-memory database; performing an
// @EntityScan; ...
@SpringBootTest // as alternative to @ContextConfiguration
//@AutoConfigureTestEntityManager
// @SpringJUnitConfig
//@AutoConfigureTestDatabase // (replace = Replace.NONE)
public class EmployeeRepositoryTest {

//	@Autowired
//	private TestEntityManager entityManager;

	@Autowired
	private EmployeeRepository employeeRepository;

	// @TestConfiguration
	// static class Configuration {
	// }

	@Test
	public void testCreateEmployee() throws Exception {
		Employee employee = new Employee("felix");
		// entityManager.persistAndFlush(employee);
		employeeRepository.save(employee);

		List<Employee> employees = employeeRepository.findAll();
		// assertThat(employees, is(empty()));
		assertThat(employees, hasSize(1));

		// assertThat from assertj: http://www.baeldung.com/introduction-to-assertj
	}

}