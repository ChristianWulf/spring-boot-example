package spring.boot.transaction;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import spring.boot.transaction.entity.LastEventId;
import spring.boot.transaction.entity.LastEventIdRepository;
import spring.boot.transaction.entity.Person;
import spring.boot.transaction.entity.PersonRepository;
import spring.boot.transaction.entity.PersonWithVersion;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ManagedEntityTest {

	@Autowired
	private PersonService personService;

	@Autowired
	private ScenarioService scenarioService;

	@Autowired
	PersonRepository personRepository;

	@Autowired
	PersonWithVersionRepository personWithVersionRepository;

	@Autowired
	PersonWithVersionService personWithVersionService;

	@Test
	void update_executes_select_query() throws Exception {
		System.out.println("Creating...");
		Person person = personService.createPerson("Christina", "Klassen");

		System.out.println("Updating...");
		person = scenarioService.updatePerson(person);

		// Optional<Person> person2 = personRepository.findById(person.getId());
		// assertThat(person2.get().getSurename(), is("Klassen"));

		System.out.println("Saving...");
		person = personRepository.save(person);
		assertThat(person.getSurename(), is("Wulf"));
	}

	@Test
	void testPersonTooLongSurename() throws Exception {
		assertThrows(DataIntegrityViolationException.class, () -> {
			// throws an exception on commit, i.e., after method execution
			personService.createPerson("Christina", "tooo-loooong");
		});
	}

	@Test
	void update_does_not_execute_select_query() throws Exception {
		System.out.println("Creating...");
		PersonWithVersion person = personWithVersionService.createPerson("Christina", "Klassen");

		System.out.println("Updating..." + person.getVersion());
		person = scenarioService.updatePerson(person);

		System.out.println("Saving..." + person.getVersion());
		person = personWithVersionRepository.save(person);
		assertThat(person.getSurename(), is("Wulf"));
	}

	@Autowired
	LastEventIdRepository lastEventIdRepository;

	@Test
	void multiple_transactions_in_loop() throws Exception {
		LastEventId lastEventId = new LastEventId();
		lastEventId = lastEventIdRepository.save(lastEventId);

		for (int i = 0; i < 10; i++) {
			lastEventId = scenarioService.performScenario(lastEventId);
			System.out.println(">>> lastEventId: " + lastEventId);

			// access db since only accesses by id are cached
			System.out.println("findAll: " + lastEventIdRepository.findAll()); 
		}
	}
}
