package chw.tutorial.springboot.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	public Employee findByName(@Param("name") String name);

}